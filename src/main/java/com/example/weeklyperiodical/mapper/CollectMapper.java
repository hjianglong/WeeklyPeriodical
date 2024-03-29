package com.example.weeklyperiodical.mapper;

import com.example.weeklyperiodical.pojo.entity.Collect;
import com.example.weeklyperiodical.pojo.vo.CollectVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CollectMapper {

    /**
     * 添加用户收藏夹
     * @param collect
     */
    int  addNew (Collect collect);

    /**
     * 根据用户名和数据连接删除收藏列表
     * @param username
     * @return
     */
    int delectById(String username,String collectlink);

    /**
     * 根据用户名清空所有收藏列表
     * @param username
     * @return
     */
    int delectByUsername(String username);

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
