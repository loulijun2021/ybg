package com.lou.cloud_office_back.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lou.cloud_office_back.common.ResultTemplate;
import com.lou.cloud_office_back.entity.ClockInfo;
import com.lou.cloud_office_back.entity.User;
import com.lou.cloud_office_back.mapper.ClockInfoMapper;
import com.lou.cloud_office_back.service.ClockInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author llj
 * @Date 2022/1/19 10:46
 * @Version 1.0
 */
@Service
public class ClockInfoServiceIpl implements ClockInfoService {

    @Autowired
    private ClockInfoMapper clockInfoMapper;

    @Override
    public ResultTemplate getAttendanceAll(int pageNum, int pageSize, String keyword,String dept,String date) {
//    public ResultTemplate getAttendanceAll(int pageNum, int pageSize, String keyword, int dept, String date) {

        ResultTemplate template = new ResultTemplate();

        PageHelper.startPage(pageNum, pageSize);
        List<ClockInfo> list = clockInfoMapper.getAttendanceAll(keyword,dept,date);
        PageInfo<ClockInfo> pageInfo = new PageInfo<>(list);
        template.setData(pageInfo);
        template.setCode(20000);
        template.setMessage("success");
        return template;

    }

    @Override
    public ResultTemplate getLeaveHistory(ClockInfo clockInfo) {
//    public ResultTemplate getAttendanceAll(int pageNum, int pageSize, String keyword, int dept, String date) {

        ResultTemplate template = new ResultTemplate();

//        PageHelper.startPage();
        List<ClockInfo> list = clockInfoMapper.getLeaveHistory(clockInfo);
//        PageInfo<ClockInfo> pageInfo = new PageInfo<>(list);
        template.setData(list);
        template.setCode(20000);
        template.setMessage("success");
        return template;

    }

    @Override
    public boolean singIn(ClockInfo clockInfo) {
        List<ClockInfo> clockInfos = clockInfoMapper.selectClock(clockInfo);
        if (clockInfos.size() > 0) {
            return false;
        }
        if (clockInfo.getName() != null) {
            clockInfoMapper.singIn(clockInfo);
            return true;
        }
        return false;
    }

    @Override
    public int singOut(ClockInfo clockInfo) {
        List<ClockInfo> clockInfos = clockInfoMapper.selectClock(clockInfo);
//        System.out.println(clockInfos);
//        System.out.println(clockInfo);
//        System.out.println(clockInfos.size());
        //今天没有签到
        if (clockInfos.size() == 0) {
            return 1;
        } else if (clockInfos.get(0).getEnd() != null ||
                clockInfos.get(0).getGoOut() == 1 ||
                clockInfos.get(0).getLeave() == 1) {
            //今天已经签离
            return 2;
        }
//        if (clockInfoMapper.singOut(clockInfo) > 0){
        if (clockInfo.getName() != null) {
            clockInfoMapper.singOut(clockInfo);
            return 3;
        } else {
            //打卡失败
            return 4;
        }
    }

    @Override
    public int addLeave(ClockInfo clockInfo) {
        List<ClockInfo> clockInfos = clockInfoMapper.selectClock(clockInfo);
        if (clockInfos.size() > 0) {
            return -1;
        } else {
            return clockInfoMapper.addLeave(clockInfo);
        }
    }
    @Override
    public int isApprove(ClockInfo clockInfo) {
        List<ClockInfo> clockInfos = clockInfoMapper.selectClock(clockInfo);
        if (clockInfos.size() > 0) {
            return -1;
        } else {
            return clockInfoMapper.isApprove(clockInfo);
        }
    }


    @Override
    public int addGoOut(ClockInfo clockInfo) {
        List<ClockInfo> clockInfos = clockInfoMapper.selectClock(clockInfo);
        if (clockInfos.size() > 0) {
            return -1;
        } else {
            return clockInfoMapper.addGoOut(clockInfo);
        }
    }
}
