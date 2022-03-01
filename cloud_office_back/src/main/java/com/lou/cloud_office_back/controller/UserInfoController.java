package com.lou.cloud_office_back.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lou.cloud_office_back.common.ResultTemplate;
import com.lou.cloud_office_back.common.TokenUtil;
import com.lou.cloud_office_back.entity.User;
import com.lou.cloud_office_back.mapper.UserInfoMapper;
import com.lou.cloud_office_back.service.UserInfoService;
import com.lou.cloud_office_back.utils.ExcelUtil;
import com.lou.cloud_office_back.utils.SendResponseHeaderUtil;
import org.apache.ibatis.annotations.Param;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
//            result.put("token", token);
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
    @RequestMapping("/resetPassword")
    @ResponseBody
    public ResponseEntity<Object> resetPassword(@Param("id") int[] id) {
        ResultTemplate template=new ResultTemplate();
        if(userInfoService.resetPassword(id)>0){
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

    @RequestMapping("/passwordChange")
    @ResponseBody
    public ResponseEntity<Object> passwordChange(@RequestBody User user) {
        ResultTemplate template=new ResultTemplate();
        if(userInfoService.passwordChange(user)>0){
            template.setMessage("success");
//            template.setData(userInfoService.insertUserInfo(user));
            template.setCode(20000);
        }else {
            template.setMessage("原密码错误");
            template.setCode(50000);
        }
        return ResponseEntity.ok().body(template);
    }

    @PostMapping("/download")
    @ResponseBody
    public ResponseEntity<Object> download(@RequestBody(required = false) Integer[] ids) throws IOException {
        ResultTemplate template=new ResultTemplate();
        List<User> users = new ArrayList<>();
        try {
            if (ids == null){
                users = userInfoService.selectAll();
            }else {
                for (Integer i : ids) {
                    users.add(userInfoService.selectId(i));
                }
            }
            ExcelUtil excelUtil = new ExcelUtil();
            excelUtil.getHSSFWorkbook(users,null);
            template.setCode(20000);
            template.setMessage("下载成功");
        }catch (Exception e){
            template.setCode(50000);
            template.setMessage("下载失败");
        }
        return ResponseEntity.ok().body(template);
    }

//    @PostMapping(path = "/download")
//    public void download(@RequestBody(required = false) String json,
//                         HttpServletResponse response){
//        //获取数据
//        JSONObject data = JSON.parseObject(json);
//        String code = data.getString("code");
//        String start = data.getString("start");
//        String end = data.getString("end");
////        System.out.println(code+" "+start+" "+end);
//
//        List<Map<String, Object>> maps = UserInfoService.getUserInfoAll("");
//
//
//        //设置excel文件名
//        String fileName = "表单.xls";
//        HSSFWorkbook wb = ExcelUtil.getHSSFWorkbook(maps, null);
//
//        //响应到客户端
//        try {
//            SendResponseHeaderUtil.setResponseHeader(response, fileName);
//            OutputStream os = response.getOutputStream();
//            wb.write(os);
//            os.flush();
//            os.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }




}
