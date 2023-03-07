package com.example.weeklyperiodical.controller;

import com.example.weeklyperiodical.pojo.dto.UsersAddNewDTO;
import com.example.weeklyperiodical.pojo.dto.UsersLoginDTO;
import com.example.weeklyperiodical.pojo.vo.UsersListItemVO;
import com.example.weeklyperiodical.security.LoginPrincipal;
import com.example.weeklyperiodical.service.IUsersService;
import com.example.weeklyperiodical.web.JsonResult;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users")
@Api(tags = "01.用户管理模块")
public class UsersController {

    @Autowired
    private IUsersService usersService;

    public UsersController() {
        log.debug("创建控制器对象：AdminController");
    }
    // http://localhost:8080/users/login
    @ApiOperation("用户登录")
    @ApiOperationSupport(order = 50)
    @PostMapping("/login")
    public JsonResult login(UsersLoginDTO usersLoginDTO) {
        log.debug("开始处理【添加用户】的请求，参数：{}", usersLoginDTO);
        String jwt = usersService.login(usersLoginDTO);
        return JsonResult.ok(jwt);
    }

    // http://localhost:8080/users/addnew
    @ApiOperation("添加用户")
    @ApiOperationSupport(order = 100)
    @PostMapping("/addnew")
    public JsonResult addNew(UsersAddNewDTO usersAddNewDTO) {
        log.debug("开始处理【添加用户】的请求，参数：{}", usersAddNewDTO);
        usersService.addNew(usersAddNewDTO);
        return JsonResult.ok();
    }

    @ApiOperation("根据id删除用户")
    @ApiOperationSupport(order = 200)
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "long")
    @PreAuthorize("hasAnyAuthority('/ams/users/delete')")
    @PostMapping("/{id:[0-9]+}/delete")
    public JsonResult delete(@PathVariable Long id,
                             @ApiIgnore @AuthenticationPrincipal LoginPrincipal loginPrincipal) {
        log.debug("开始处理【根据id删除删除用户】的请求，参数：{}", id);
        log.debug("当事人：{}",loginPrincipal.getId());
        log.debug("当事人的用户名：{}",loginPrincipal.getUsername());
        usersService.delete(id);
        return JsonResult.ok();
    }

    @ApiOperation("启用用户")
    @ApiOperationSupport(order = 310)
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "long")
    @PreAuthorize("hasAnyAuthority('/ams/users/update')")
    @PostMapping("/{id:[0-9]+}/enable")
    public JsonResult setEnable(@PathVariable Long id) {
        log.debug("开始处理【启用用户】的请求，参数：{}", id);
        usersService.setEnable(id);
        return JsonResult.ok();
    }

    @ApiOperation("禁用用户")
    @ApiOperationSupport(order = 311)
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "long")
    @PreAuthorize("hasAnyAuthority('/ams/users/update')")
    @PostMapping("/{id:[0-9]+}/disable")
    public JsonResult setDisable(@PathVariable Long id) {
        log.debug("开始处理【禁用用户】的请求，参数：{}", id);
        usersService.setDisable(id);
        return JsonResult.ok();
    }

    @ApiOperation("查询用户列表")
    @ApiOperationSupport(order = 420)
    @PreAuthorize("hasAnyAuthority('/ams/users/read')")
    @GetMapping("")
    public JsonResult list() {
        log.debug("开始处理【查询用户列表】的请求，参数：无");
        List<UsersListItemVO> list = usersService.list();
        return JsonResult.ok(list);
    }



}
