package com.example.weeklyperiodical.service;

import com.example.weeklyperiodical.ex.ServiceException;
import com.example.weeklyperiodical.pojo.dto.UsersAddNewDTO;
import com.example.weeklyperiodical.pojo.dto.UsersLoginDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Slf4j
@SpringBootTest
public class UsersServiceTexts {
    @Autowired
    IUsersService usersService;

    @Test
    void login() {
        UsersLoginDTO adminLoginDTO = new UsersLoginDTO();
        adminLoginDTO.setUsername("hjl ");
        adminLoginDTO.setPassword("123456");

        try {
            String jwt = usersService.login(adminLoginDTO);
            log.debug("登录成功,JWT={}",jwt);
        } catch (Throwable e) {
            // 由于不确定Spring Security会抛出什么类型的异常
            // 所以，捕获的是Throwable
            // 并且，在处理时，应该打印信息，以了解什么情况下会出现哪种异常
            e.printStackTrace();
        }
    }
    @Test
    void addNew() {
        UsersAddNewDTO usersAddNewDTO = new UsersAddNewDTO();
        usersAddNewDTO.setSchoole("测试数据");
        usersAddNewDTO.setUsername("测试数据");
        usersAddNewDTO.setPassword("123456");

        try {
            usersService.addNew(usersAddNewDTO);
            log.debug("添加数据完成！");
        } catch (ServiceException e) {
            log.debug("添加数据失败！名称已经被占用！");
        }
    }

    @Test
    void delete() {
        Long id = 6L;

        try {
            usersService.delete(id);
            log.debug("删除用户完成！");
        } catch (ServiceException e) {
            log.debug("删除用户失败！具体原因请参见日志！");
        }
    }

    @Test
    void setEnable() {
        Long id = 1L;

        try {
            usersService.setEnable(id);
            log.debug("启用管理员完成！");
        } catch (ServiceException e) {
            log.debug("启用管理员失败！具体原因请参见日志！");
        }
    }

    @Test
    void setDisable() {
        Long id = 1L;

        try {
            usersService.setDisable(id);
            log.debug("禁用管理员完成！");
        } catch (ServiceException e) {
            log.debug("禁用管理员失败！具体原因请参见日志！");
        }
    }

    @Test
    void list() {
        List<?> list = usersService.list();
        log.debug("查询列表完成，列表中的数据的数量：{}", list.size());
        for (Object item : list) {
            log.debug("{}", item);
        }
    }










}
