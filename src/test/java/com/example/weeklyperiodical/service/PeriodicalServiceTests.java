package com.example.weeklyperiodical.service;


import com.example.weeklyperiodical.ex.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Slf4j
@SpringBootTest
public class PeriodicalServiceTests {

    @Autowired
    IPeriodicalService iPeriodicalService;

    @Test
    void list() {
        List<?> list = iPeriodicalService.list();
        log.debug("查询列表完成，列表中的数据的数量：{}", list.size());
        for (Object item : list) {
            log.debug("{}", item);
        }
    }

    @Test
    void list2() {
        List<?> list = iPeriodicalService.listTitle();
        log.debug("查询列表完成，列表中的数据的数量：{}", list.size());
        for (Object item : list) {
            log.debug("{}", item);
        }
    }

    @Test
    void getStandardById() {
        String period = "月刊";

        try {
            iPeriodicalService.getStandardByPeriod(period);
            log.debug("获取数据完成！");
        } catch (ServiceException e) {
            log.debug("获取数据失败！具体原因请参见日志！");
        }
    }

    @Test
    void getStandardBytitle() {
        String title = "临床护理研究";

        try {
            iPeriodicalService.getStandardBytitle(title);
            log.debug("获取数据完成！");
        } catch (ServiceException e) {
            log.debug("获取数据失败！具体原因请参见日志！");
        }
    }

    @Test
    void getStandardBysponsor() {
        String sponsor = "中国人民大学";

        try {
            iPeriodicalService.getStandardBysponsor(sponsor);
            log.debug("获取数据完成！");
        } catch (ServiceException e) {
            log.debug("获取数据失败！具体原因请参见日志！");
        }
    }

    @Test
    void getTitleQs(){
        String title = "中小学教育";
        String qs = "202331";

        try {
            iPeriodicalService.getTitleQs(title,qs);
            log.debug("获取数据完成！");
        } catch (ServiceException e) {
            log.debug("获取数据失败！具体原因请参见日志！");
        }
    }

    @Test
    void getTitleQsBdb(){
        String title = "中小学教育";
        String qs = "202331";

        try {
            iPeriodicalService.getTitleQsBdb(title,qs);
            log.debug("获取数据完成！");
        } catch (ServiceException e) {
            log.debug("获取数据失败！具体原因请参见日志！");
        }
    }

    @Test
    void list1() {
        List<?> list = iPeriodicalService.getMulu("中小学教育","202331");
        log.debug("查询列表完成，列表中的数据的数量：{}", list.size());
        for (Object item : list) {
            log.debug("{}", item);
        }
    }

    @Test
    void getText(){
        String mulu = "【中国医学影像技术】同型半胱氨酸联合易栓三项对胎儿生长受限的预测价值 ";

        try {
            iPeriodicalService.getText(mulu);
            log.debug("获取数据完成！");
        } catch (ServiceException e) {
            log.debug("获取数据失败！具体原因请参见日志！");
        }
    }

    @Test
    void getTitle(){
        String mulu = "【临床护理研究】丹皮-蒲公英及其配伍对慢性应激小鼠胃肠动力的影响 ";

        try {
            iPeriodicalService.getTitle(mulu);
            log.debug("获取数据完成！");
        } catch (ServiceException e) {
            log.debug("获取数据失败！具体原因请参见日志！");
        }
    }

    @Test
    void getTitleText(){
        String title = "丹皮-蒲公英及其配伍对慢性应激小鼠胃肠动力的影响 ";

        try {
            iPeriodicalService.getTitleText(title);
            log.debug("获取数据完成！");
        } catch (ServiceException e) {
            log.debug("获取数据失败！具体原因请参见日志！");
        }
    }
}
