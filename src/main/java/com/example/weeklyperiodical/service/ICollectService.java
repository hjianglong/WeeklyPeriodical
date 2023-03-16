package com.example.weeklyperiodical.service;

import com.example.weeklyperiodical.pojo.entity.Collect;
import com.example.weeklyperiodical.pojo.vo.CollectVO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface ICollectService {

    /**
     * 添加用户收藏夹
     * @param collect
     */
    void addNew (Collect collect);

    /**
     * 根据用户名和数据连接删除收藏列表
     * @param username
     * @return
     */
    void delectById(String username,String collectlink);

    /**
     * 根据用户名清空所有收藏列表
     * @param username
     * @return
     */
    void delectByUsername(String username);

    /**
     * 根据用户查看收藏列表
     * @param username
     * @return
     */
    List<CollectVO> listUsername(String username);

    /**
     * 查看所有收藏列表
     * @return
     */
    List<CollectVO> list();

}
