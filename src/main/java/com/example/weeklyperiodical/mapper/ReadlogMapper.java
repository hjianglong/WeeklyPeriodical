package com.example.weeklyperiodical.mapper;

import com.example.weeklyperiodical.pojo.entity.Readlog;
import org.springframework.stereotype.Repository;

@Repository
public interface ReadlogMapper {

    /**
     *
     * @param readlog
     * @return
     */
    int addReadlog(Readlog readlog);
}
