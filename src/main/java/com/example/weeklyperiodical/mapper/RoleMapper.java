package com.example.weeklyperiodical.mapper;

import com.example.weeklyperiodical.pojo.vo.RoleListItemVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleMapper {
    /**
     * 查询角色列表
     *
     * @return 角色列表
     */
    List<RoleListItemVO> list();
}
