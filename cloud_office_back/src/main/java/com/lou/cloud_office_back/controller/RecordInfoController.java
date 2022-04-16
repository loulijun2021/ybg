package com.lou.cloud_office_back.controller;

import com.lou.cloud_office_back.common.ResultTemplate;
import com.lou.cloud_office_back.entity.ClockInfo;
import com.lou.cloud_office_back.entity.Record;
import com.lou.cloud_office_back.service.RecordInfoService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author llj
 * @Date 2022/1/19 14:34
 * @Version 1.0
 */
@RestController
@RequestMapping("/record")
public class RecordInfoController {

    @Autowired
    private RecordInfoService recordInfoService;

    //员工添加日志
    @PostMapping("/addRecord")
    public ResultTemplate singIn(@RequestBody Record record) {
        ResultTemplate<Object> result = new ResultTemplate<>();
        System.out.println(record);
        if (recordInfoService.addRecord(record) > 0) {
            result.setMessage("success");
//            result.setData("打卡成功");
            result.setCode(20000);
        } else {
            result.setMessage("fail");
//            result.setData("请勿重复打卡！！");
            result.setCode(50000);
        }
        return result;
    }

    //获取个人日志信息
    @RequestMapping("/queryAllByName")
    @ResponseBody
    public ResultTemplate queryAllByName(@RequestBody Record record) {

        ResultTemplate template = recordInfoService.queryAllByName(record);
        return template;
    }

    //获取所有人
    @RequestMapping("/queryAll")
    @ResponseBody
    public ResultTemplate queryAll() {

        ResultTemplate<Object> result = new ResultTemplate<>();
        if (recordInfoService.queryAll().size()>0) {
            result.setMessage("success");
            result.setData(recordInfoService.queryAll());
            result.setCode(20000);
        }
        return result;
    }

}
