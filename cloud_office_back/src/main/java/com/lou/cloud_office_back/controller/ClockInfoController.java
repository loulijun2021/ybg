package com.lou.cloud_office_back.controller;

import com.lou.cloud_office_back.common.ResultTemplate;
import com.lou.cloud_office_back.entity.ClockInfo;
import com.lou.cloud_office_back.entity.User;
import com.lou.cloud_office_back.service.ClockInfoService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

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
    public ResultTemplate singIn(@RequestBody ClockInfo clockInfo) {
        ResultTemplate<Object> result = new ResultTemplate<>();
        System.out.println(clockInfo);
        if (clockInfoService.singIn(clockInfo)) {
            result.setMessage("success");
            result.setData("打卡成功");
            result.setCode(20000);
        } else {
            result.setMessage("fail");
            result.setData("请勿重复打卡！！");
            result.setCode(50000);
        }
        return result;
    }


    @PostMapping("/singOut")
    public ResultTemplate singOut(@RequestBody ClockInfo clockInfo) {
        ResultTemplate<Object> result = new ResultTemplate<>();

        int i = clockInfoService.singOut(clockInfo);
        if (i == 3) {
            result.setCode(20000);
            result.setMessage("success");
            result.setData("签退成功");
        } else if (i == 1) {
            result.setMessage("false");
            result.setData("今天还没有签到！！");
            result.setCode(50000);
        } else if (i == 2) {
            result.setMessage("false");
            result.setData("今天已经签离，请不要重复签离！！");
            result.setCode(50000);
        }
        return result;
    }

    //申请请假
    @PostMapping("/leave")
    public ResultTemplate leave(@RequestBody ClockInfo clockInfo) {
        ResultTemplate<Object> result = new ResultTemplate<>();

        int i = clockInfoService.addLeave(clockInfo);
        if (i > 0) {
            result.setCode(20000);
            result.setMessage("success");
            result.setData("登记成功");
        } else if (i == 0) {
            result.setMessage("false");
            result.setData("请假失败！！");
            result.setCode(50000);
        } else if (i == -1) {
            result.setMessage("false");
            result.setData("今天已经记录，请不要重复申请！！");
            result.setCode(50000);
        }
        return result;
    }

//审批请假
    @PostMapping("/isApprove")
    public ResultTemplate isApprove(@RequestBody ClockInfo clockInfo) {
        ResultTemplate<Object> result = new ResultTemplate<>();

        int i = clockInfoService.isApprove(clockInfo);
        if (i > 0) {
            result.setCode(20000);
            result.setMessage("success");
            result.setData("登记成功");
        } else if (i == 0) {
            result.setMessage("false");
            result.setData("请假失败！！");
            result.setCode(50000);
        } else if (i == -1) {
            result.setMessage("false");
            result.setData("今天已经记录，请不要重复申请！！");
            result.setCode(50000);
        }
        return result;
    }
    //获取个人请假历史信息
    @RequestMapping("/getLeaveHistory")
    @ResponseBody
    public ResultTemplate getLeaveHistory(@RequestBody ClockInfo clockInfo) {

        ResultTemplate template=clockInfoService.getLeaveHistory(clockInfo);
        return template;
    }

    @PostMapping("/goOut")
    public ResultTemplate goOut(@RequestBody ClockInfo clockInfo) {
        ResultTemplate<Object> result = new ResultTemplate<>();

        int i = clockInfoService.addGoOut(clockInfo);
        if (i > 0) {
            result.setCode(20000);
            result.setMessage("success");
            result.setData("登记成功");
        } else if (i == 0) {
            result.setMessage("false");
            result.setData("登记失败！！");
            result.setCode(50000);
        } else if (i == -1) {
            result.setMessage("false");
            result.setData("今天已经登记，请不要重复申请！！");
            result.setCode(50000);
        }
        return result;
    }

    @RequestMapping("/getAttendanceAll")
    @ResponseBody
    public ResultTemplate getAttendanceAll(@RequestParam(name = "pageNum") int pageNum,
                                           @RequestParam(name = "pageSize") int pageSize,
                                           @RequestParam(name = "keyword",required =false) String keyword,
                                           @RequestParam(name = "dept",required = false) String dept,
                                           @RequestParam(name = "date",required = false) String date
    ) {


//        ResultTemplate template = clockInfoService.getAttendanceAll(pageNum, pageSize, keyword, dept, date);
        ResultTemplate template = clockInfoService.getAttendanceAll(pageNum, pageSize, keyword,dept,date);
        return template;
    }

//    @RequestMapping("/attendance")
//    @ResponseBody
//    public ResponseEntity<Object> attendance(@RequestBody ClockInfo clockInfo) {
//        ResultTemplate template=new ResultTemplate();
//        if(clockInfoService.attendance(clockInfo)>0){
//            template.setMessage("success");
//            template.setData(clockInfoService.attendance(clockInfo));
//            template.setCode(20000);
//        }else {
//            template.setMessage("原密码错误");
//            template.setCode(50000);
//        }
//        return ResponseEntity.ok().body(template);
//    }


}
