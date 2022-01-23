package com.lou.cloud_office_back.service;

import com.lou.cloud_office_back.entity.Record;

import java.util.List;

/**
 * @Author llj
 * @Date 2022/1/19 14:26
 * @Version 1.0
 */
public interface RecordInfoService {

    List<Record> queryAll();

    int addRecord(Record record);

    int updateRecord(int id);

    int deleteRecord(int id);

}
