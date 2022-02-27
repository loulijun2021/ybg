package com.lou.cloud_office_back.service;

import com.lou.cloud_office_back.entity.Dept;

import java.util.List;

public interface DeptService {
    List<Dept> getDeptAll();

    int insertDept(Dept dept);

    int updateDept(Dept dept);

    int deleteDept(int id);
}
