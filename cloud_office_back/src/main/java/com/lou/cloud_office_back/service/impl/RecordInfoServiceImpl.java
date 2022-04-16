package com.lou.cloud_office_back.service.impl;

import com.lou.cloud_office_back.common.ResultTemplate;
import com.lou.cloud_office_back.entity.ClockInfo;
import com.lou.cloud_office_back.entity.Record;
import com.lou.cloud_office_back.mapper.RecordInfoMapper;
import com.lou.cloud_office_back.service.RecordInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author llj
 * @Date 2022/1/19 14:27
 * @Version 1.0
 */
@Service
public class RecordInfoServiceImpl implements RecordInfoService {

    @Autowired
    private RecordInfoMapper recordInfoMapper;

    @Override
    public ResultTemplate queryAllByName(Record record) {

        ResultTemplate template = new ResultTemplate();

        List<Record> list = recordInfoMapper.queryAllByName(record);
        template.setData(list);
        template.setCode(20000);
        template.setMessage("success");
        return template;
    }

  @Override
    public List<Record> queryAll() {
        return recordInfoMapper.queryAll();
    }

    @Override
    public int addRecord(Record record) {
        return recordInfoMapper.addRecord(record);
    }

    @Override
    public int updateRecord(int id) {
        return recordInfoMapper.updateRecord(id);
    }

    @Override
    public int deleteRecord(int id) {
        return recordInfoMapper.deleteRecord(id);
    }
}
