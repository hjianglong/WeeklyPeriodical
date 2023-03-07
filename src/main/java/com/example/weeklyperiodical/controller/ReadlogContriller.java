package com.example.weeklyperiodical.controller;

import com.example.weeklyperiodical.pojo.dto.ReadlogAddNewDTO;
import com.example.weeklyperiodical.pojo.dto.UsersAddNewDTO;
import com.example.weeklyperiodical.service.IReadlogService;
import com.example.weeklyperiodical.web.JsonResult;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/Readlog")
@Api(tags = "03. 用户浏览记录模块")
public class ReadlogContriller {

    @Autowired
    IReadlogService readlogService;

    public ReadlogContriller() {
        log.debug("创建控制器对象：ReadlogContriller");
    }

    @ApiOperation("添加用户浏览记录")
    @ApiOperationSupport(order = 420)
    @GetMapping("addReadlogs")
    public JsonResult addReadlogs(ReadlogAddNewDTO readlogAddNewDTO, HttpServletRequest request){
        log.debug("开始处理【添加用户】的请求，参数：{}", readlogAddNewDTO);
        readlogService.addReadlogs(readlogAddNewDTO,request);
        return JsonResult.ok();
    }
















}
