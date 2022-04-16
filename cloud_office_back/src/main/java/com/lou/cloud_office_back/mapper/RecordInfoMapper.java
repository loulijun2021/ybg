package com.lou.cloud_office_back.mapper;

import com.lou.cloud_office_back.entity.Record;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;

/**
 * @Author llj
 * @Date 2022/1/19 14:06
 * @Version 1.0
 */
@Mapper
public interface RecordInfoMapper {

    List<Record> queryAllByName(Record record);

    List<Record> queryAll();

    int addRecord(Record record);

    int updateRecord(int id);

    int deleteRecord(int id);
}
