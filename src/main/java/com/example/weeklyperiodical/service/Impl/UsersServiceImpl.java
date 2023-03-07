package com.example.weeklyperiodical.service.Impl;

import com.alibaba.fastjson.JSON;
import com.example.weeklyperiodical.ex.ServiceException;
import com.example.weeklyperiodical.mapper.UsersMapper;
import com.example.weeklyperiodical.mapper.UsersRoleMapper;
import com.example.weeklyperiodical.pojo.dto.UsersAddNewDTO;
import com.example.weeklyperiodical.pojo.dto.UsersLoginDTO;
import com.example.weeklyperiodical.pojo.entity.Users;
import com.example.weeklyperiodical.pojo.entity.UsersRole;
import com.example.weeklyperiodical.pojo.vo.UsersListItemVO;
import com.example.weeklyperiodical.pojo.vo.UsersStandardVO;
import com.example.weeklyperiodical.security.UsersDetails;
import com.example.weeklyperiodical.service.IUsersService;
import com.example.weeklyperiodical.web.ServiceCode;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class UsersServiceImpl implements IUsersService {

    @Value("${csmall.jwt.secret-key}")
    private String secretKey;
    @Value("${csmall.jwt.duration-in-minute}")
    private long durationInMinute;
    @Autowired
    UsersMapper usersMapper;
    @Autowired
    UsersRoleMapper usersRoleMapper;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UsersServiceImpl() {
        log.debug("创建业务对象：UsersServiceImpl");
    }

    @Override
    public String login(UsersLoginDTO adminLoginDTO) {
        log.debug("开始处理【用户登录】的业务，参数：{}", adminLoginDTO);
        // 执行认证
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                adminLoginDTO.getUsername(), adminLoginDTO.getPassword());
        Authentication authenticateResult
                = authenticationManager.authenticate(authentication);
        log.debug("认证通过！");
        log.debug("认证结果：{}",authenticateResult);// 注意：此认证结果中的Principal就是UserDetailsServiceImpl中返回的UserDetails对象

        //从认证结果中取出将要存入JWT中的数据
        Object principal = authenticateResult.getPrincipal();
        UsersDetails usersDetails= (UsersDetails) principal;
        Long id =usersDetails.getUid();
        String username = usersDetails.getUsername();
        Collection<GrantedAuthority>authorities = usersDetails.getAuthorities();
        String authoritiesJsonString = JSON.toJSONString(authorities);
        log.debug("认证结果中的当事人ID：{}",id);
        log.debug("认证结果中的当事人username：{}",username);
        log.debug("认证结果中的当事人authorities：{}",authorities);
        log.debug("认证结果中的当事人authoritiesJsonString：{}",authoritiesJsonString);

        //将认证通过后得到的认证信息存入SecurityContext中
        //[注意]注释一下2行代码后,在未完成JWT验证流程之前,用户的登录将不可用
        //SecurityContext securityContext = SecurityContextHolder.getContext();
        //securityContext.setAuthentication(authenticateResult);

        //==== 生成并返回JWT ====
        //JWT的过期时间
        Date date = new Date(System.currentTimeMillis() + durationInMinute * 60 * 1000);

        //你要存入到JWT中的数据
        Map<String,Object> claims = new HashMap<>();
        claims.put("id",id);
        claims.put("username",username);
        claims.put("authorities",authoritiesJsonString);
        String jwt = Jwts.builder()//获取JwtBuilder,准备构建JWT数据
                //[1]Header:主要配置alg（algorithm：算法）和typ(type：类型)属性
                .setHeaderParam("alg","HS256")
                .setHeaderParam("typ","JWT")
                //[2]Payload:主要配置Claims，把你要存入的数据放进去
                .setClaims(claims)
                //[3]Signature:主要配置JWT的过期时间、签名的算法和secretkey
                .setExpiration(date)
                .signWith(SignatureAlgorithm.HS256,secretKey)
                //完成
                .compact();//取得JWT数据
        log.debug("即将返回jwt数据:{}",jwt);
        return jwt;
    }

    @Override
    public void addNew(UsersAddNewDTO usersAddNewDTO) {
        log.debug("开始处理【添加用户】的业务，参数：{}", usersAddNewDTO);
        Long[] roleIds = usersAddNewDTO.getRoleIds();
        //不允许新的用户分配1号角色
        for (int i = 0; i < roleIds.length; i++) {
            if (roleIds[i]==1){
                String message = "添加用户失败，方法访问(不允许为管理员分配1号角色)！";
                log.warn(message);
                throw new ServiceException(ServiceCode.ERR_CONFLICT, message);
            }
        }
        {
            // 从参数对象中取出用户名
            String username = usersAddNewDTO.getUsername();
            // 调用adminMapper.countByUsername()执行统计
            int count = usersMapper.countByUsername(username);
            // 判断统计结果是否大于0
            if (count > 0) {
                // 是：抛出异常（ERR_CONFLICT）
                String message = "添加用户失败，尝试使用的用户名已经被占用！";
                log.warn(message);
                throw new ServiceException(ServiceCode.ERR_CONFLICT, message);
            }
        }

        // 创建Users对象
        Users users = new Users();
        // 复制参数DTO对象中的属性到实体对象中
        BeanUtils.copyProperties(usersAddNewDTO, users);
        //将原密码加密
        String rawPassword = users.getPassword();
        String encodedPassword = passwordEncoder.encode(rawPassword);
        users.setPassword(encodedPassword);
        // 调用usersMapper.insert()方法插入管理员数据
        int rows = usersMapper.insert(users);
        if (rows != 1) {
            String message = "添加用户失败，服务器忙，请稍后再尝试！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_INSERT, message);
        }

        // 准备批量插入用户与角色的关联数据
        Long usersid = users.getUid();
        UsersRole[] adminRoleList = new UsersRole[roleIds.length];
        for (int i = 0; i < roleIds.length; i++) {
            UsersRole usersRole = new UsersRole();
            usersRole.setUsersid(usersid);
            usersRole.setRoleid(roleIds[i]);
            adminRoleList[i] = usersRole;
        }
        rows = usersRoleMapper.insertBatch(adminRoleList);
        if (rows != roleIds.length) {
            String message = "添加用户失败，服务器忙，请稍后再尝试！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_INSERT, message);
        }
    }

    @Override
    public void delete(Long id) {
        log.debug("开始处理【根据id删除删除用户】的业务，参数：{}", id);
        //不允许删除1号用户员
        if (id==1){
            String message = "删除用户失败，尝试访问的数据不存在！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_NOT_FOUND, message);
        }

        // 根据用户id检查用户数据是否存在
        UsersStandardVO queryResult = usersMapper.getStandardById(id);
        if (queryResult == null) {
            String message = "删除用户失败，尝试访问的数据不存在！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_NOT_FOUND, message);
        }

        // 执行删除--用户表
        log.debug("即将执行删除，参数：{}", id);
        int rows = usersMapper.deleteById(id);
        if (rows != 1) {
            String message = "删除用户失败，服务器忙，请稍后再尝试！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_DELETE, message);
        }

        // 执行删除--用户与角色的关联表
        rows = usersRoleMapper.deleteByAdminId(id);
        if (rows < 1) {
            String message = "删除用户失败，服务器忙，请稍后再尝试！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_DELETE, message);
        }
    }

    @Override
    public void setEnable(Long id) {
        updateEnableById(id, 1);
    }

    @Override
    public void setDisable(Long id) {
        updateEnableById(id, 0);
    }
    private void updateEnableById(Long uid, Integer enable) {
        String[] enableText = {"禁用", "启用"};
        log.debug("开始处理【" + enableText[enable] + "用户】的业务，用户ID：{}，目标状态：{}", uid, enable);
        //不允许调整1号用户的启用状态
        if (uid==1){
            String message = enableText[enable] + "有用户失败，尝试访问的数据不存在！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_NOT_FOUND, message);
        }

        // 根据用户id检查管理员数据是否存在
        UsersStandardVO queryResult = usersMapper.getStandardById(uid);
        if (queryResult == null) {
            String message = enableText[enable] + "用户失败，尝试访问的数据不存在！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_NOT_FOUND, message);
        }

        // 检查用户数据的当前状态是否与参数enable表示的状态相同
        if (queryResult.getEnable().equals(enable)) {
            String message = enableText[enable] + "用户失败，当前用户已经是"
                    + enableText[enable] + "状态！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_CONFLICT, message);
        }

        // 创建Users对象
        Users admin = new Users();
        // 将方法的2个参数封装到Users对象中
        admin.setUid(uid);
        admin.setEnable(enable);
        // 调用usersMapper对象的update()方法执行修改
        log.debug("即将修改数据，参数：{}", admin);
        usersMapper.update(admin);
    }

    @Override
    public List<UsersListItemVO> list() {
        log.debug("开始处理【查询用户列表】的业务，参数：无");
        List<UsersListItemVO> list = usersMapper.list();
        Iterator<UsersListItemVO> iterator = list.iterator();
        while (iterator.hasNext()) {
            UsersListItemVO usersListItemVO = iterator.next();
            if (usersListItemVO.getUid() == 1) {
                iterator.remove();
            }
        }
        return list;
    }


}
