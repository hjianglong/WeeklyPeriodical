package com.example.weeklyperiodical.mapper;

import com.example.weeklyperiodical.pojo.entity.Users;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@SpringBootTest
public class UsersMapperTexts {

    @Autowired(required = true)
    UsersMapper usersMapper;

    @Test
    void insert(){
        Users users = new Users();
        users.setSchoole("侯江垄");
        users.setUsername("hjl");
        users.setPassword("123456");
        log.debug("插入数据之前，参数：{}", users);
        int rows = usersMapper.insert(users);
        log.debug("插入数据完成，受影响的行数：{}", rows);
        log.debug("插入数据之后，参数：{}", users);
    }

    @Test
    void insertBatch() {
        List<Users> adminList = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            Users users = new Users();
            users.setUsername("批量插入测试Admin" + i);
            users.setSchoole("批量插入测试数据的简介" + i);
            users.setPassword("测试昵称" + i);
            adminList.add(users);
        }
        int rows = usersMapper.insertBatch(adminList);
        log.debug("批量插入完成，受影响的行数：{}", rows);
    }

    @Test
    void deleteById() {
        Long id = 1L;
        int rows = usersMapper.deleteById(id);
        log.debug("删除完成，受影响的行数：{}", rows);
    }

    @Test
    void deleteByIds() {
        Long[] ids = {3L,4L};
        int rows = usersMapper.deleteByIds(ids);
        log.debug("批量删除完成，受影响的行数：{}", rows);
    }

    @Test
    void update() {
        Users users = new Users();
        users.setUid(3L);
        users.setSchoole("新-测试数据001");

        int rows = usersMapper.update(users);
        log.debug("更新完成，受影响的行数：{}", rows);
    }

    @Test
    void count() {
        int count = usersMapper.count();
        log.debug("统计完成，表中的数据的数量：{}", count);
    }

    @Test
    void countByUsername() {
        String username = "hjl";
        int count = usersMapper.countByUsername(username);
        log.debug("根据用户名【{}】统计管理员账号的数量：{}", username, count);
    }

    @Test
    void getStandardById() {
        Long uid = 1L;
        Object queryResult = usersMapper.getStandardById(uid);
        log.debug("根据id【{}】查询数据详情完成，查询结果：{}", uid, queryResult);
    }

    @Test
    void getLoginInfoByUsername() {
        String username = "sys";
        Object queryResult = usersMapper.getLoginInfoByUsername(username);
        log.debug("根据用户名【{}】查询数据详情完成，查询结果：{}", username, queryResult);
    }

    @Test
    void list() {
        List<?> list = usersMapper.list();
        log.debug("查询列表完成，列表中的数据的数量：{}", list.size());
        for (Object item : list) {
            log.debug("{}", item);
        }
    }


}


















