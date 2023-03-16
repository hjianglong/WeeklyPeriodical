package com.example.weeklyperiodical.service.Impl;

import com.example.weeklyperiodical.ex.ServiceException;
import com.example.weeklyperiodical.mapper.CollectMapper;
import com.example.weeklyperiodical.pojo.entity.Collect;
import com.example.weeklyperiodical.pojo.vo.CollectVO;
import com.example.weeklyperiodical.pojo.vo.UsersListItemVO;
import com.example.weeklyperiodical.service.ICollectService;
import com.example.weeklyperiodical.web.ServiceCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

@Slf4j
@Service
public class CollectServiceImpl implements ICollectService{

    @Autowired
    CollectMapper collectMapper;

    @Override
    public void addNew(Collect collect) {
        log.debug("开始处理【添加用户收藏夹】的业务，参数：{}",collect);
        int rows = collectMapper.addNew(collect);
        if (rows != 1) {
            String message = "添加用户收藏夹失败，服务器忙，请稍后再尝试！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_INSERT, message);
        }
    }

    @Override
    public void delectById(String username,String collectlink) {
        log.debug("开始处理【根据用户名和数据连接删除收藏列表】的业务，参数：{},{}",username,collectlink);
        int rows = collectMapper.delectById(username,collectlink);
        if (rows < 0) {
            String message = "根据用户名和数据连接删除收藏列表失败，服务器忙，请稍后再尝试！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_INSERT, message);
        }
    }

    @Override
    public void delectByUsername(String username) {
        log.debug("开始处理【根据用户名清空所有收藏列表】的业务，参数：{}",username);
        int rows = collectMapper.delectByUsername(username);
        if (rows < 0) {
            String message = "根据用户名清空所有收藏列表失败，服务器忙，请稍后再尝试！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_INSERT, message);
        }
    }

    @Override
    public List<CollectVO> listUsername(String username) {
        log.debug("开始处理【根据用户查看收藏列表】的业务，参数：{}",username);
        List<CollectVO> list = collectMapper.listUsername(username);
        return list;
    }

    @Override
    public List<CollectVO> list() {
        log.debug("开始处理【查看所有收藏列表】的业务，参数：无");
        List<CollectVO> list = collectMapper.list();
        return list;
    }


}
