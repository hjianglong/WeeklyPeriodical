package com.example.weeklyperiodical.service.Impl;

import com.example.weeklyperiodical.mapper.RoleMapper;
import com.example.weeklyperiodical.pojo.vo.RoleListItemVO;
import com.example.weeklyperiodical.service.IRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

/**
 * 处理角色数据的业务实现类
 *
 * @author java@tedu.cn
 * @version 0.0.1
 */
@Slf4j
@Service
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<RoleListItemVO> list() {
        log.debug("开始处理【查询角色列表】的业务，无参数");
        List<RoleListItemVO> list = roleMapper.list();
        Iterator<RoleListItemVO> iterator = list.iterator();
        while (iterator.hasNext()) {
            RoleListItemVO roleListItemVO = iterator.next();
            if (roleListItemVO.getId() == 1) {
                iterator.remove();
            }
        }
        return list;
    }

}
