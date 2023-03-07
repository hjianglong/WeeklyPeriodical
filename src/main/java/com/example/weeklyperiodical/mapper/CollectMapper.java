package com.example.weeklyperiodical.mapper;

import com.example.weeklyperiodical.pojo.entity.Collect;
import org.springframework.stereotype.Repository;

@Repository
public interface CollectMapper {

    /**
     * 添加用户收藏夹
     * @param collect
     */
    int  addNew (Collect collect);

}
