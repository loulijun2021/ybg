package com.lou.cloud_office_back.service;

import com.lou.cloud_office_back.entity.ClockInfo;


/**
 * @Author llj
 * @Date 2022/1/19 10:44
 * @Version 1.0
 */
public interface ClockInfoService {
    boolean singIn(ClockInfo clockInfo);

    int singOut(ClockInfo clockInfo);

    int addLeave(ClockInfo clockInfo);

    int addGoOut(ClockInfo clockInfo);
}
