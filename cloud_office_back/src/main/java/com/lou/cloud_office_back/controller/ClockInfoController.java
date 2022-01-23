package com.lou.cloud_office_back.controller;

import com.lou.cloud_office_back.common.ResultTemplate;
import com.lou.cloud_office_back.entity.ClockInfo;
import com.lou.cloud_office_back.service.ClockInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author llj
 * @Date 2022/1/19 10:52
 * @Version 1.0
 */
@RestController
@RequestMapping("/clock")
public class ClockInfoController {

    @Autowired
    private ClockInfoService clockInfoService;

    @PostMapping("/singIn")
    public ResultTemplate singIn(@RequestBody ClockInfo clockInfo){
        ResultTemplate<Object> result = new ResultTemplate<>();
        System.out.println(clockInfo);
        if (clockInfoService.singIn(clockInfo)){
            result.setMessage("success");
            result.setData("打卡成功");
            result.setCode(20000);
        }else {
            result.setMessage("fail");
            result.setData("请勿重复打卡！！");
            result.setCode(50000);
        }
        return  result;
    }


    @PostMapping("/singOut")
    public ResultTemplate singOut(@RequestBody ClockInfo clockInfo){
        ResultTemplate<Object> result = new ResultTemplate<>();

        int i = clockInfoService.singOut(clockInfo);
        if ( i == 3){
            result.setCode(20000);
            result.setMessage("success");
            result.setData("签退成功");
        }else if (i == 1){
            result.setMessage("false");
            result.setData("今天还没有签到！！");
            result.setCode(50000);
        }else if (i == 2){
            result.setMessage("false");
            result.setData("今天已经签离，请不要重复签离！！");
            result.setCode(50000);
        }
        return  result;
    }


    @PostMapping("/leave")
    public ResultTemplate leave(@RequestBody ClockInfo clockInfo){
        ResultTemplate<Object> result = new ResultTemplate<>();

        int i = clockInfoService.addLeave(clockInfo);
        if ( i > 0){
            result.setCode(20000);
            result.setMessage("success");
            result.setData("登记成功");
        }else if (i == 0){
            result.setMessage("false");
            result.setData("请假失败！！");
            result.setCode(50000);
        }else if (i == -1){
            result.setMessage("false");
            result.setData("今天已经记录，请不要重复申请！！");
            result.setCode(50000);
        }
        return  result;
    }

    @PostMapping("/goOut")
    public ResultTemplate goOut(@RequestBody ClockInfo clockInfo){
        ResultTemplate<Object> result = new ResultTemplate<>();

        int i = clockInfoService.addGoOut(clockInfo);
        if ( i > 0){
            result.setCode(20000);
            result.setMessage("success");
            result.setData("登记成功");
        }else if (i == 0){
            result.setMessage("false");
            result.setData("登记失败！！");
            result.setCode(50000);
        }else if (i == -1){
            result.setMessage("false");
            result.setData("今天已经登记，请不要重复申请！！");
            result.setCode(50000);
        }
        return  result;
    }



}
