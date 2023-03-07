package com.example.weeklyperiodical.service;

import com.example.weeklyperiodical.pojo.dto.UsersAddNewDTO;
import com.example.weeklyperiodical.pojo.dto.UsersLoginDTO;
import com.example.weeklyperiodical.pojo.vo.UsersListItemVO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 处理用户数据的业务接口
 */
@Transactional
public interface IUsersService {

    /**
     * 管理员登录
     *
     * @param adminLoginDTO 封装了登录参数的对象
     * @return 管理员登录成功后将得到的JWT
     */
    String login(UsersLoginDTO adminLoginDTO);

    /**
     * 添加用户
     *
     * @param usersAddNewDTO 用户数据
     */
    void addNew(UsersAddNewDTO usersAddNewDTO);

    /**
     * 删除管理员
     *
     * @param id 管理员id
     */
    void delete(Long id);

    /**
     * 启用管理员
     *
     * @param id 管理员id
     */
    void setEnable(Long id);

    /**
     * 禁用管理员
     *
     * @param id 管理员id
     */
    void setDisable(Long id);

    /**
     * 查询管理员列表
     *
     * @return 管理员列表
     */
    List<UsersListItemVO> list();
}
