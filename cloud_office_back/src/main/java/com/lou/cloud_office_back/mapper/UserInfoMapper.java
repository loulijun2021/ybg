package com.lou.cloud_office_back.mapper;

import com.lou.cloud_office_back.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author llj
 * @Date 2022/1/18 16:34
 * @Version 1.0
 */
@Mapper
public interface UserInfoMapper {

    List<User> getUserInfoAll(String keyword);

    List<User> queryUserInfo(String username,String password);

    int addUser(User user);

    int insertUserInfo(User user);

    int deleteUserInfo(int[] id);

    int resetPassword(int[] id);

    int updateUserInfo(User user);

    int passwordChange(User user);
}
