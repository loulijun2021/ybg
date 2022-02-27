package com.lou.cloud_office_back.service.impl;

import com.lou.cloud_office_back.entity.Dept;
import com.lou.cloud_office_back.entity.User;
import com.lou.cloud_office_back.mapper.DeptMapper;
import com.lou.cloud_office_back.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptMapper deptMapper;

    @Override
    public List<Dept> getDeptAll(){
        return deptMapper.getDeptAll();
    }

    @Override
    public int insertDept(Dept dept) {
        return deptMapper.insertDept(dept);
    }
    @Override
    public int updateDept(Dept dept) {
        return deptMapper.updateDept(dept);
    }
    @Override
    public int deleteDept(int id) {
        return deptMapper.deleteDept(id);
    }
}
