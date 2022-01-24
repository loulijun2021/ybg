package com.lou.cloud_office_back.entity;

import lombok.Data;

/**
 * @Author llj
 * @Date 2022/1/18 16:33
 * @Version 1.0
 */
@Data
public class User {
    private int id;
    private String username;
    private String password;
    private int role;
    private String dept;
    private int deptId;
    private String newPassword;
}
