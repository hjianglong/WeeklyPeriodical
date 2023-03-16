package com.example.weeklyperiodical.controller;

import com.example.weeklyperiodical.pojo.entity.Collect;
import com.example.weeklyperiodical.pojo.vo.CollectVO;
import com.example.weeklyperiodical.pojo.vo.UsersListItemVO;
import com.example.weeklyperiodical.service.ICollectService;
import com.example.weeklyperiodical.web.JsonResult;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/collect")
@Api(tags = "05.收藏夹")
public class CollectController {

    public CollectController() {
        log.debug("创建控制器对象：CollectController");
    }

    @Autowired
    private ICollectService collectService;

    @ApiOperation("添加用户收藏夹")
    @ApiOperationSupport(order = 100)
    @PostMapping("/addnew")
    public JsonResult addNew(Collect collect){
        log.debug("开始处理【添加用户收藏夹】的请求，参数：{}", collect);
        collectService.addNew(collect);
        return JsonResult.ok();
    }

    @ApiOperation("根据用户名和数据连接删除收藏列表")
    @ApiOperationSupport(order = 200)
    @PostMapping("/delect")
    public JsonResult delectById(String username,String collectlink) {
        log.debug("开始处理【根据用户名和数据连接删除收藏列表】的请求，参数：{}", username,collectlink);
        collectService.delectById(username,collectlink);
        return JsonResult.ok();
    }

    @ApiOperation("根据用户名清空所有收藏列表")
    @ApiOperationSupport(order = 300)
    @PostMapping("/delectByUsername")
    public JsonResult delectByUsername(String username) {
        log.debug("开始处理【根据用户名清空所有收藏列表】的请求，参数：{}", username);
        collectService.delectByUsername(username);
        return JsonResult.ok();
    }

    @ApiOperation("根据用户查看收藏列表")
    @ApiOperationSupport(order = 400)
    @PostMapping("/listUsername")
    public JsonResult listUsername(String username) {
        log.debug("开始处理【根据用户查看收藏列表】的请求，参数：无");
        List<CollectVO> list = collectService.listUsername(username);
        return JsonResult.ok(list);
    }

    @ApiOperation("查看所有收藏列表")
    @ApiOperationSupport(order = 500)
    @PostMapping("/")
    public JsonResult list() {
        log.debug("开始处理【查看所有收藏列表】的请求，参数：无");
        List<CollectVO> list = collectService.list();
        return JsonResult.ok(list);
    }







}
