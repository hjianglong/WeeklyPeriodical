package com.example.weeklyperiodical.mapper;

import com.example.weeklyperiodical.pojo.entity.PeriodicaData;
import com.example.weeklyperiodical.pojo.entity.Periodical;
import com.example.weeklyperiodical.pojo.vo.PeriodicaDataVO;
import com.example.weeklyperiodical.pojo.vo.PeriodicalVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Slf4j
@SpringBootTest
public class PeriodicalMapperTests {

    @Autowired
    PeriodicalMapper periodicalMapper;

    @Test
    void list() {
        List<?> list = periodicalMapper.list();
        log.debug("查询列表完成，列表中的数据的数量：{}", list.size());
        for (Object item : list) {
            log.debug("{}", item);
        }
    }

    @Test
    void list1(){
        String period = "周刊";
        List<?> list= periodicalMapper.getStandardByPeriod(period);
        log.debug("period【{}】查询数据详情完成，查询结果：{}", period, list);
        for (Object item : list) {
            log.debug("{}", item);
        }
    }

    @Test
    void list2(){
        String title = "临床护理研究";
        List<?> list= periodicalMapper.getStandardBytitle(title);
        log.debug("title【{}】查询数据详情完成，查询结果：{}", title, list);
        for (Object item : list) {
            log.debug("{}", item);
        }
    }

    @Test
    void list3(){
        String sponsor = "中国人民大学";
        List<?> list = periodicalMapper.getStandardBysponsor(sponsor);
        log.debug("title【{}】查询数据详情完成，查询结果：{}", sponsor, list);
        for (Object item : list) {
            log.debug("{}", item);
        }
    }

    @Test
    void list4() {
        List<?> list = periodicalMapper.listTitle();
        log.debug("查询列表完成，列表中的数据的数量：{}", list.size());
        for (Object item : list) {
            log.debug("{}", item);
        }
    }

    @Test
    void av(){
        PeriodicalVO periodicalVO = new PeriodicalVO();
        periodicalVO.setQs("202332");
        periodicalVO.setTitle("临床护理研究");
        String img = periodicalMapper.getTitleQs("临床护理研究","202332");
        log.debug("根据【{}】查询数据详情完成，查询结果：{}", periodicalVO, img);
    }

    @Test
    void ae(){
        PeriodicalVO periodicalVO = new PeriodicalVO();
        periodicalVO.setQs("202332");
        periodicalVO.setTitle("临床护理研究");
        String img = periodicalMapper.getTitleQs("临床护理研究","202332");
        log.debug("根据【{}】查询数据详情完成，查询结果：{}", periodicalVO, img);
    }

    @Test
    void aq(){
        PeriodicaDataVO periodicaDataVO = new PeriodicaDataVO();
        periodicaDataVO.setMgzname("中国注册会计师");
        periodicaDataVO.setQs("202331");
        List<?> list= periodicalMapper.getMulu("中国注册会计师","202331");
        log.debug("根据【{}】查询数据详情完成，查询结果：{}", periodicaDataVO, list);
    }

    @Test
    void ce(){
        String message = ("【2021年05期】浅析如何推动信息技术在小学语文习作教学中的融合与渗透 ");
        String text =  periodicalMapper.getText(message);
        log.debug("根据【{}】查询数据详情完成，查询结果：{}",message, text);
    }

    @Test
    void cr(){
        String message = ("【临床护理研究】机器人系统及其辅助治疗肺癌的研究进展");
        String text =  periodicalMapper.getTitle(message);
        log.debug("根据【{}】查询数据详情完成，查询结果：{}",message, text);
    }

    @Test
    void ct(){
        String message = ("机器人系统及其辅助治疗肺癌的研究进展");
        String text =  periodicalMapper.getTitleText(message);
        log.debug("根据【{}】查询数据详情完成，查询结果：{}",message, text);
    }


}
