package com.example.weeklyperiodical.mapper;

import com.example.weeklyperiodical.pojo.entity.Users;
import com.example.weeklyperiodical.pojo.vo.UsersListItemVO;
import com.example.weeklyperiodical.pojo.vo.UsersLoginInfoVo;
import com.example.weeklyperiodical.pojo.vo.UsersStandardVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersMapper {
    /**
     * 插入用户数据
     *
     * @param  users 用户数据
     * @return 受影响的行数
     */
    int insert(Users users);

    /**
     * 批量插入用户数据
     *
     * @param usersList  用户列表
     * @return 受影响的行数
     */
    int insertBatch(List<Users> usersList);

    /**
     * 根据用户id删除用户数据
     * @param id 用户id
     * @return 受影响的行数
     */
    int deleteById(Long id);

    /**
     * 根据若干个id批量删除用户数据
     *
     * @param ids 若干个用户id的数组
     * @return 受影响的行数
     */
    int deleteByIds(Long[] ids);

    /**
     * 根据用户id修改用户的数据
     *
     * @param users 封装了用户id和新的数据的对象
     * @return 受影响的行数
     */
    int update(Users users);

    /**
     * 统计用户表中的数据的数量
     *
     * @return 用户表中的数据的数量
     */
    int count();


    /**
     * 根据用户名统计用户的数量
     *
     * @param username 用户名
     * @return 匹配用户名的管理员的数据
     */
    int countByUsername(String username);

    /**
     * 根据用户id查询用户详情
     * @param id 用户id
     * @return 匹配的用户详情，如果没有匹配的数据，则返回null
     */
    UsersStandardVO getStandardById(Long id);

    /**
     * 根据用户的用户名查询用户登录信息
     * @param username 用户名
     * @return 匹配的登录信息，如果没有匹配的数据，则返回null
     */
    UsersLoginInfoVo getLoginInfoByUsername(String username);

    /**
     * 查询用户列表
     *
     * @return 用户列表
     */
    List<UsersListItemVO> list();


}
