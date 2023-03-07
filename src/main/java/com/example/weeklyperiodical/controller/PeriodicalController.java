package com.example.weeklyperiodical.controller;

import com.example.weeklyperiodical.pojo.entity.Periodical;
import com.example.weeklyperiodical.pojo.vo.PeriodicaDataVO;
import com.example.weeklyperiodical.service.IPeriodicalService;
import com.example.weeklyperiodical.web.JsonResult;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/Periodical")
@Api(tags = "03. 期刊管理模块")
public class PeriodicalController {

    @Autowired
    private IPeriodicalService periodicalService;

    public PeriodicalController() {
        log.debug("创建控制器对象：PeriodicalController");
    }

    @ApiOperation("根据期刊类型查找列表")
    @ApiOperationSupport(order = 100)
    @ApiImplicitParam(name = "period", value = "期刊类型", required = true, dataType = "String")
    @GetMapping("/{period}")
    public JsonResult getStandardByPeriod(@PathVariable String period) {
        log.debug("开始处理【根据期刊类型查找列表】的请求，参数:{}",period);
        List<Periodical> list = periodicalService.getStandardByPeriod(period);
        return JsonResult.ok(list);
    }

    @ApiOperation("根据期刊名查找列表")
    @ApiOperationSupport(order = 101)
    @ApiImplicitParam(name = "title", value = "期刊名", required = true, dataType = "String")
    @GetMapping("/{title}/bytitle")
    public JsonResult getStandardBytitle(@PathVariable String title) {
        log.debug("开始处理【根据期刊名查找列表】的请求，参数:{}",title);
        List<Periodical> list = periodicalService.getStandardBytitle(title);
        return JsonResult.ok(list);
    }

    @ApiOperation("根据出版社查找列表")
    @ApiOperationSupport(order = 102)
    @ApiImplicitParam(name = "sponsor", value = "出版社", required = true, dataType = "String")
    @GetMapping("/{sponsor}/bysponsor")
    public JsonResult getStandardBysponsor(@PathVariable String sponsor) {
        log.debug("开始处理【根据出版社查找列表】的请求，参数:{}",sponsor);
        List<Periodical> list = periodicalService.getStandardBysponsor(sponsor);
        return JsonResult.ok(list);
    }

    @ApiOperation("根据期刊名和期数查询图片")
    @ApiOperationSupport(order = 103)
    @GetMapping("/img")
    public JsonResult getTitleQs(String title , String qs){
        log.debug("开始处理【根据期刊名和期数查询图片】的请求，参数:{},{}",title ,qs);
        String img = periodicalService.getTitleQs(title,qs);
        return JsonResult.ok(img);
    }

    @ApiOperation("根据期刊名和期刊号查询bdb内容")
    @ApiOperationSupport(order = 104)
    @GetMapping("/Bdb")
    public JsonResult getTitleQsBdb(String title , String qs){
        log.debug("开始处理【根据期刊名和期刊号查询pdf内容】的请求，参数:{},{}",title ,qs);
        String img = periodicalService.getTitleQsBdb(title,qs);
        return JsonResult.ok(img);
    }

    @ApiOperation("根据期刊名和期刊号查询目录")
    @ApiOperationSupport(order = 105)
    @GetMapping("/ml")
    public JsonResult getMulu(String mgzname , String qs) {
        log.debug("开始处理【根据期刊名和期刊号查询目录】的请求，参数:{},{}",mgzname,qs);
        List<PeriodicaDataVO> list = periodicalService.getMulu(mgzname,qs);
        return JsonResult.ok(list);
    }

    @ApiOperation("根据目录查找标题")
    @ApiOperationSupport(order = 106)
    @GetMapping("/title")
    public JsonResult getTitle (String mulu){
        log.debug("开始处理【根据期刊名和期刊号查询目录】的请求，参数:{}",mulu);
        String text= periodicalService.getTitle(mulu);
        return JsonResult.ok(text);
    }

    @ApiOperation("根据目录查找正文")
    @ApiOperationSupport(order = 107)
    @GetMapping("/text")
    public JsonResult getText (String mulu){
        log.debug("开始处理【根据期刊名和期刊号查询目录】的请求，参数:{}",mulu);
        String text= periodicalService.getText(mulu);
        return JsonResult.ok(text);
    }

    @ApiOperation("根据标题查找正文")
    @ApiOperationSupport(order = 108)
    @GetMapping("/TitleText")
    public JsonResult getTitleText (String title){
        log.debug("开始处理【根据标题查找正文】的请求，参数:{}",title);
        String text= periodicalService.getTitleText(title);
        return JsonResult.ok(text);
    }




    @ApiOperation("查询期刊列表(去重)")
    @ApiOperationSupport(order = 600)
    @GetMapping("")
    public JsonResult list() {
        log.debug("开始处理【查询期刊列表】的请求，无参数");
        List<Periodical> list = periodicalService.list();
        return JsonResult.ok(list);
    }

    @ApiOperation("查询期刊标题(按照期数排序)")
    @ApiOperationSupport(order = 650)
    @GetMapping("listTitle")
    public JsonResult listTitle() {
        log.debug("开始处理【查询期刊列表】的请求，无参数");
        List<PeriodicaDataVO> list = periodicalService.listTitle();
        return JsonResult.ok(list);
    }




}
