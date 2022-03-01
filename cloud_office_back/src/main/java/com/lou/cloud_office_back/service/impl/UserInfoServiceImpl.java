package com.lou.cloud_office_back.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lou.cloud_office_back.common.ResultTemplate;
import com.lou.cloud_office_back.entity.User;
import com.lou.cloud_office_back.mapper.UserInfoMapper;
import com.lou.cloud_office_back.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author llj
 * @Date 2022/1/18 16:39
 * @Version 1.0
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public User queryUser(String username, String password) {
        List<User> users = userInfoMapper.queryUserInfo(username, password);
//        System.out.println(users);
        if (users.size() > 0) {
            return users.get(0);
        }
        return null;
    }

    @Override
    public int register(User user) {
        return userInfoMapper.addUser(user);
    }

//    @Override
//    public List<User> getUserInfoAll(int pageNum, int pageSize, String keyword) {
////        ResultTemplate template= new ResultTemplate();
//        PageHelper.startPage(pageNum, pageSize);
////        List<User> list = userInfoMapper.getUserInfoAll(keyword);
////        PageInfo<User> pageInfo = new PageInfo<>(list);
////        System.out.println(pageInfo);
//        return userInfoMapper.getUserInfoAll(keyword);
//    }

    @Override
    public ResultTemplate getUserInfoAll(int pageNum, int pageSize, String keyword) {
        ResultTemplate template=new ResultTemplate();
        PageHelper.startPage(pageNum, pageSize);
        List<User> list = userInfoMapper.getUserInfoAll(keyword);
        PageInfo<User> pageInfo = new PageInfo<>(list);
        template.setData(pageInfo);
        template.setCode(20000);
        template.setMessage("success");
        return template;
    }


    @Override
    public int insertUserInfo(User user) {
        return userInfoMapper.insertUserInfo(user);
    }

    @Override
    public int deleteUserInfo(int[] id) {
        return userInfoMapper.deleteUserInfo(id);
    }

    @Override
    public int resetPassword(int[] id) {
        return userInfoMapper.resetPassword(id);
    }

    @Override
    public int updateUserInfo(User user) {
        return userInfoMapper.updateUserInfo(user);
    }

    @Override
    public int passwordChange(User user){
        return userInfoMapper.passwordChange(user);
    }

    @Override
    public List<User> selectAll() {
        return userInfoMapper.getUserInfoAll("");
    }

    @Override
    public User selectId(Integer id) {
        return userInfoMapper.getUserById(id);
    }
}
