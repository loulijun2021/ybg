package com.lou.cloud_office_back.service;

import com.lou.cloud_office_back.common.ResultTemplate;
import com.lou.cloud_office_back.entity.User;

import java.util.List;

/**
 * @Author llj
 * @Date 2022/1/18 16:37
 * @Version 1.0
 */
public interface UserInfoService {
    User queryUser(String username, String password);

    int register(User user);

    ResultTemplate getUserInfoAll(int pageNum, int pageSize, String keyword);

    int insertUserInfo(User user);

    int deleteUserInfo(int[] id);

    int updateUserInfo(User user);
}
