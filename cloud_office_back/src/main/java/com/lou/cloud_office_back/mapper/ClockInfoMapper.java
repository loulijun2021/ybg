package com.lou.cloud_office_back.mapper;

import com.lou.cloud_office_back.entity.ClockInfo;
import com.lou.cloud_office_back.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author llj
 * @Date 2022/1/19 9:55
 * @Version 1.0
 */
@Mapper
public interface ClockInfoMapper {

//    List<ClockInfo> getAttendanceAll(String keyword,int dept,String date);
    List<ClockInfo> getAttendanceAll(String keyword,String dept,String date);

    List<ClockInfo> queryAll();

    List<ClockInfo> selectClock(ClockInfo clockInfo);

    int singIn(ClockInfo clockInfo);

    int singOut(ClockInfo clockInfo);

    int addLeave(ClockInfo clockInfo);

    int addGoOut(ClockInfo clockInfo);
}
