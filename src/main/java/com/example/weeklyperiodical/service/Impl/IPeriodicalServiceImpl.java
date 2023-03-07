package com.example.weeklyperiodical.service.Impl;

import com.example.weeklyperiodical.ex.ServiceException;
import com.example.weeklyperiodical.mapper.PeriodicalMapper;
import com.example.weeklyperiodical.pojo.entity.Periodical;
import com.example.weeklyperiodical.pojo.vo.PeriodicaDataVO;
import com.example.weeklyperiodical.pojo.vo.PeriodicalVO;
import com.example.weeklyperiodical.pojo.vo.RoleListItemVO;
import com.example.weeklyperiodical.service.IPeriodicalService;
import com.example.weeklyperiodical.web.ServiceCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Iterator;
import java.util.List;
@Slf4j
@Service
public class IPeriodicalServiceImpl implements IPeriodicalService {

    @Autowired
    PeriodicalMapper periodicalMapper;

    @Override
    public List<Periodical> list() {
        log.debug("开始处理【查询角色列表】的业务，无参数");
        List<Periodical> list = periodicalMapper.list();
        return list;
    }

    @Override
    public List<PeriodicaDataVO> listTitle() {
        log.debug("开始处理【查询标题列表】的业务，无参数");
        List<PeriodicaDataVO> list = periodicalMapper.listTitle();
        return list;
    }

    @Override
    public List<Periodical> getStandardByPeriod(String period) {
        log.debug("开始处理【根据期刊类型查找列表】的业务，参数:{},",period);
        List<Periodical> list = periodicalMapper.getStandardByPeriod(period);
        if (list == null) {
            String message = "根据期刊类型查找列表失败，尝试访问的数据不存在！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_NOT_FOUND, message);
        }
        log.debug("即将返回期刊列表：{}", list);
        return list;
    }

    @Override
    public List<Periodical> getStandardBytitle(String title) {
        log.debug("开始处理【根据期刊名查找列表】的业务，参数:{},",title);
        List<Periodical> list = periodicalMapper.getStandardBytitle(title);
        if (list == null) {
            String message = "根据期刊类型查找列表失败，尝试访问的数据不存在！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_NOT_FOUND, message);
        }
        log.debug("即将返回期刊列表：{}", list);
        return list;
    }

    @Override
    public List<Periodical> getStandardBysponsor(String sponsor) {
        log.debug("开始处理【根据出版社查找列表】的业务，参数:{},",sponsor);
        List<Periodical> list = periodicalMapper.getStandardBysponsor(sponsor);
        if (list == null) {
            String message = "根据期刊类型查找列表失败，尝试访问的数据不存在！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_NOT_FOUND, message);
        }
        log.debug("即将返回期刊列表：{}", list);
        return list;
    }

    @Override
    public String getTitleQs(String title , String qs) {
        log.debug("开始处理【根据期刊名和期刊号查询图片内容】的业务，参数:{},",title , qs);
        String img= periodicalMapper.getTitleQs(title,qs);
        if (title == null || qs == null) {
            String message = "根据期刊名和期刊号查询图片内容失败，尝试访问的数据不存在！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_NOT_FOUND, message);
        }
        log.debug("即将返回期刊图片：{}", img);
        return img;
    }

    @Override
    public String getTitleQsBdb(String title, String qs) {
        log.debug("开始处理【根据期刊名和期刊号查询pdf内容】的业务，参数:{},",title , qs);
        String img= periodicalMapper.getTitleQsBdb(title,qs);
        if (title == null || qs == null) {
            String message = "根据期刊名和期刊号查询pdf内容失败，尝试访问的数据不存在！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_NOT_FOUND, message);
        }
        log.debug("即将返回期刊PDF：{}", img);
        return img;
    }

    @Override
    public List<PeriodicaDataVO> getMulu(String mgzname, String qs) {
        log.debug("开始处理【根据期刊名和期刊号查询目录】的业务，参数:{},",mgzname,qs);
        List<PeriodicaDataVO> list = periodicalMapper.getMulu(mgzname,qs);
        if (list == null) {
            String message = "根据期刊名和期刊号查询目录失败，尝试访问的数据不存在！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_NOT_FOUND, message);
        }
        log.debug("即将返回期刊列表：{}", list);
        return list;
    }

    @Override
    public String getTitle(String mulu) {
        log.debug("开始处理【根据目录查找标题】的业务，参数:{},",mulu);
        String mee= periodicalMapper.getTitle(mulu);
        if (mee == null) {
            String message = "根据目录查找标题，尝试访问的数据不存在！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_NOT_FOUND, message);
        }
        log.debug("即将返回标题：{}", mee);
        return mee;
    }


    @Override
    public String getText (String mulu) {
        log.debug("开始处理【根据目录查找正文】的业务，参数:{},",mulu);
        String mee= periodicalMapper.getText(mulu);
        if (mee == null) {
            String message = "根据目录查找正文，尝试访问的数据不存在！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_NOT_FOUND, message);
        }
        log.debug("即将返回正文：{}", mee);
        return mee;
    }

    @Override
    public String getTitleText(String title) {
        log.debug("开始处理【根据标题查找正文】的业务，参数:{},",title);
        String mee= periodicalMapper.getTitleText(title);
        if (mee == null) {
            String message = "根据目录查找正文，尝试访问的数据不存在！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_NOT_FOUND, message);
        }
        log.debug("即将返回正文：{}", mee);
        return mee;
    }

}
