package com.lou.cloud_office_back.mapper;

import com.lou.cloud_office_back.entity.Dept;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DeptMapper {
    List<Dept> getDeptAll();

    int insertDept(Dept dept);

    int updateDept(Dept dept);

    int deleteDept(int id);

}
