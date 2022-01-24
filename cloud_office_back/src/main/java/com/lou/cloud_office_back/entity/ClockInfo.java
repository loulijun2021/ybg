package com.lou.cloud_office_back.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @Author llj
 * @Date 2022/1/19 9:58
 * @Version 1.0
 */
@Data
public class ClockInfo {
    private int id;
    private String name;
//    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", locale = "zh",timezone="GMT+8")
    private String start;
//    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",locale = "zh", timezone="GMT+8")
    private String end;
    private int leave;//请假
    private int goOut;//外出
    private String other;//备注

    private String dept;
//    private String date;

}
