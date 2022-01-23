package com.lou.cloud_office_back.controller;

import com.alibaba.fastjson.JSONObject;
import com.lou.cloud_office_back.common.ResultTemplate;
import com.lou.cloud_office_back.common.TokenUtil;
import com.lou.cloud_office_back.entity.User;
import com.lou.cloud_office_back.service.UserInfoService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;


/**
 * @Author llj
 * @Date 2022/1/18 16:42
 * @Version 1.0
 */
@RestController
@RequestMapping("/user")
public class UserInfoController {
    @Autowired
    private UserInfoService userInfoService;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody User user){
        JSONObject result = new JSONObject();
        ResultTemplate template = new ResultTemplate();
        User res = userInfoService.queryUser(user.getUsername(), user.getPassword());
//        System.out.println(res);
        if (res != null) {
            String token = TokenUtil.sign(res.getUsername());
            result.put("token", token);
            result.put("role", res.getRole());
            result.put("id",res.getId());
            result.put("username", res.getUsername());
            template.setData(result);
            template.setCode(20000);
            template.setMessage("success");
        }
//        Map<String,Object> map = new HashMap<>();
//        result.put("token",token);
//        result.put("role",res.getRole());
//        return  ResponseEntity.ok().body(result);
        return ResponseEntity.ok().body(template);
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody User user){
        ResultTemplate template = new ResultTemplate();
            if(userInfoService.register(user)>0){
                template.setMessage("success");
                template.setCode(20000);
            }else {
                template.setMessage("fail");
                template.setCode(50000);
            }
            return ResponseEntity.ok().body(template);
//         JSONObject result = new JSONObject();
//         if (userInfoService.register(user) > 0){
//             result.put("message","success");
//             result.put("content","插入成功！！");
//         }else {
//             result.put("result","false");
//             result.put("content","插入失败！！");
//         }
//         return ResponseEntity.ok().body(result);
    }

    @RequestMapping("/logout")
    @ResponseBody
    public ResponseEntity<Object>  logout(HttpServletRequest request , HttpServletResponse response) {
//        Map<String, Object> result = new HashMap<>();
////		result.put("data",null);
//        result.put("code",20000);
//        result.put("message","登出成功!");
//        return result ;
        ResultTemplate resultTemplate=new ResultTemplate();
        resultTemplate.setMessage("success");
        resultTemplate.setCode(20000);
        return  ResponseEntity.ok().body(resultTemplate);
    }

//    @RequestMapping("/getUserInfoAll")
//    @ResponseBody
//    public ResponseEntity<Object> getUserInfoAll(int pageNum, int pageSize,String keyword) {
//        ResultTemplate template=new ResultTemplate();
//        if(userInfoService.getUserInfoAll(pageNum,pageSize,keyword).size()>0){
//            template.setMessage("success");
//            template.setData(userInfoService.getUserInfoAll(pageNum,pageSize,keyword));
//            template.setCode(20000);
//        }else {
//            template.setMessage("fail");
//            template.setCode(50000);
//        }
//        return ResponseEntity.ok().body(template);
//    }

    @RequestMapping("/getUserInfoAll")
    @ResponseBody
    public ResultTemplate getUserInfoAll(@Param("pageNum")int pageNum,@Param("pageSize")int pageSize,@Param("keyword")String keyword) {

            ResultTemplate template=userInfoService.getUserInfoAll(pageNum,pageSize,keyword);

//            template.setData(userInfoService.getUserInfoAll(pageNum,pageSize,keyword));
//            template.setCode(20000);
//            template.setMessage("获取成功");
//        }catch (Exception e){
//            template.setCode(50000);
//            template.setMessage(e.getMessage());
//        }
        return template;
    }

    @RequestMapping("/insertUserInfo")
    @ResponseBody
    public ResponseEntity<Object> insertUserInfo(@RequestBody User user) {
        ResultTemplate template=new ResultTemplate();
        if(userInfoService.insertUserInfo(user)>0){
            template.setMessage("success");
//            template.setData(userInfoService.insertUserInfo(user));
            template.setCode(20000);
        }else {
            template.setMessage("fail");
            template.setCode(50000);
        }
        return ResponseEntity.ok().body(template);
    }

    @RequestMapping("/deleteUserInfo")
    @ResponseBody
    public ResponseEntity<Object> deleteUserInfo(@Param("id") int[] id) {
        ResultTemplate template=new ResultTemplate();
        if(userInfoService.deleteUserInfo(id)>0){
            template.setMessage("success");
//            template.setData(userInfoService.insertUserInfo(user));
            template.setCode(20000);
        }else {
            template.setMessage("fail");
            template.setCode(50000);
        }
        return ResponseEntity.ok().body(template);
    }
    @RequestMapping("/updateUserInfo")
    @ResponseBody
    public ResponseEntity<Object> updateUserInfo(@RequestBody User user) {
        ResultTemplate template=new ResultTemplate();
        if(userInfoService.updateUserInfo(user)>0){
            template.setMessage("success");
//            template.setData(userInfoService.insertUserInfo(user));
            template.setCode(20000);
        }else {
            template.setMessage("fail");
            template.setCode(50000);
        }
        return ResponseEntity.ok().body(template);
    }




}
