package com.example.weeklyperiodical.mapper;

import com.example.weeklyperiodical.pojo.entity.Collect;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class CollectMapperTests {
    @Autowired
    CollectMapper collectMapper;
    
    @Test
    void insert(){
        Collect collect = new Collect();
        collect.setUsername("root");
        collect.setCollectlink("/%E6%88%98%E5%9C%BA%E7%8E%AF%E5%A2%83%E6%95%B0%E6%8D%AE%E5%BA%93/html/news.html?id=46&cur=1");
        collect.setTitle("从巴格达到伊斯坦布尔");
        log.debug("插入数据之前，参数：{}", collect);
        int rows = collectMapper.addNew(collect);
        log.debug("插入数据完成，受影响的行数：{}", rows);
        log.debug("插入数据之后，参数：{}", collect);
    }
}
