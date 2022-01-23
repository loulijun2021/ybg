package com.lou.cloud_office_back.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @Author llj
 * @Date 2022/1/19 14:08
 * @Version 1.0
 */
@Data
public class Record {
    private int id;
    private String name;
    private String record;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date date;
}
