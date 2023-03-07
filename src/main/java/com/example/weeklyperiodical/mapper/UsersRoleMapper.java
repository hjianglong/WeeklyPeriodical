package com.example.weeklyperiodical.mapper;

import com.example.weeklyperiodical.pojo.entity.UsersRole;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRoleMapper {
    /**
     * 批量插入用户与角色的关联数据
     *
     * @param usersRoleList 若干个用户与角色的关联数据的集合
     * @return 受影响的行数
     */
    int insertBatch(UsersRole[] usersRoleList);

    /**
     * 根据用户id删除关联数据
     * @param Usersid 用户id
     * @return 受影响的行数
     */
    int deleteByAdminId(Long Usersid);


}
