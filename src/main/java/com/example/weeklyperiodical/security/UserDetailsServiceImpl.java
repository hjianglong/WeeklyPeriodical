package com.example.weeklyperiodical.security;

import com.example.weeklyperiodical.mapper.UsersMapper;
import com.example.weeklyperiodical.pojo.vo.UsersLoginInfoVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UsersMapper usersMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        log.debug("Spring Security调用了loadUserByUsername()方法，参数：{}", s);

        UsersLoginInfoVo loginInfo = usersMapper.getLoginInfoByUsername(s);
        log.debug("从数据库查询用户名【{}】匹配的信息，结果：{}", s, loginInfo);

        if (loginInfo == null) {
            return null; // 暂时
        }

        // 创建权限列表
        // AdminDetails的构造方法要求是Collection<? extends GrantedAuthority>类型的
        // 在Mapper查询结果中的权限是List<String>类型的，所以需要遍历再创建得到所需的权限列表
        List<String> permissions = loginInfo.getPermissions();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (String permission : permissions) {
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(permission);
            authorities.add(authority);
        }

        // 创建AdminDetails类型的对象
        // 此类型是基于User类型扩展的，可以有自定义属性，例如id
        UsersDetails adminDetails = new UsersDetails(
                loginInfo.getUid(), loginInfo.getUsername(), loginInfo.getPassword(),
                loginInfo.getEnable() == 1, authorities);

//        UserDetails userDetails = User.builder()
//                .username(loginInfo.getUsername())
//                .password(loginInfo.getPassword())
//                .disabled(loginInfo.getEnable() == 0)
//                .accountLocked(false) // 账号是否已锁定
//                .accountExpired(false) // 账号是否过期
//                .credentialsExpired(false) // 凭证是否过期
//                .authorities(loginInfo.getPermissions().toArray(new String[]{})) // 权限
//                .build();
        log.debug("即将向Spring Security返回UserDetails对象：{}", adminDetails);
        return adminDetails;
    }
}
