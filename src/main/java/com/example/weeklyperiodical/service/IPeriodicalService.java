package com.example.weeklyperiodical.service;

import com.example.weeklyperiodical.pojo.entity.Periodical;
import com.example.weeklyperiodical.pojo.vo.PeriodicaDataVO;
import com.example.weeklyperiodical.pojo.vo.PeriodicalVO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface IPeriodicalService {

    /**
     * 查询简介列表
     * @return 简介列表
     */
    List<Periodical> list();

    /**
     * 查询标题列表（按期数排序）
     * @return
     */
    List<PeriodicaDataVO> listTitle ();

    /**
     * 根据期刊类型查找列表
     * @param period 期刊类型
     * @return
     */
    List<Periodical> getStandardByPeriod(String period);

    /**
     * 根据期刊名查找列表
     * @param title 期刊名
     * @return
     */
    List<Periodical> getStandardBytitle (String title);

    /**
     * 根据出版社查找列表
     * @param sponsor 出版社
     * @return
     */
    List<Periodical> getStandardBysponsor(String sponsor);

    /**
     * 根据期刊名和期刊号查询图片内容
     * @param  title ,  qs 期刊名和期数
     * @return
     */
    String getTitleQs(String title , String qs);

    /**
     * 根据期刊名和期刊号查询pdf内容
     * @param title
     * @param qs
     * @return
     */
    String getTitleQsBdb(String title , String qs);

    /**
     * 根据期刊名和期刊号查询目录
     * @param mgzname
     * @param qs
     * @return
     */
    List<PeriodicaDataVO> getMulu(String mgzname , String qs);

    /**
     * 根据目录查找标题
     * @param mulu
     * @return
     */
    String getTitle (String mulu);

    /**
     * 根据目录查找正文
     * @param mulu
     * @return
     */
    String getText (String mulu);

    /**
     * 跟据标题查找正文
     * @param title
     * @return
     */
    String getTitleText(String title);


}
