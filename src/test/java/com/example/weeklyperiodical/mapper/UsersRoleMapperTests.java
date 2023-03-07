package com.example.weeklyperiodical.mapper;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class UsersRoleMapperTests {

    @Autowired
    UsersRoleMapper usersRoleMapper;

    @Test
    void insertBatch() {

    }

    @Test
    void deleteByAdminId() {
        Long adminId = 1L;
        int rows = usersRoleMapper.deleteByAdminId(adminId);
        log.debug("删除完成，受影响的行数：{}", rows);
    }
}
