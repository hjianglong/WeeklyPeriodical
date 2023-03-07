package com.example.weeklyperiodical.service;

import com.example.weeklyperiodical.pojo.dto.ReadlogAddNewDTO;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

@Transactional
public interface IReadlogService {

    /**
     *
     * @param readlogAddNewDTO
     * @return
     */
  public void addReadlogs(ReadlogAddNewDTO readlogAddNewDTO,HttpServletRequest request);

}
