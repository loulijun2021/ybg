package com.lou.cloud_office_back.controller;

import com.lou.cloud_office_back.common.ResultTemplate;
import com.lou.cloud_office_back.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/dept")
public class DeptController {
    @Autowired
    private DeptService deptService;

    @RequestMapping("/getDeptAll")
    @ResponseBody
    public ResponseEntity<Object> getDeptAll() {
        ResultTemplate resultTemplate=new ResultTemplate();
        if (deptService.getDeptAll().size()>0){
            resultTemplate.setMessage("success");
            resultTemplate.setCode(20000);
            resultTemplate.setData(deptService.getDeptAll());
        }else {
            resultTemplate.setMessage("fail");
            resultTemplate.setCode(50000);
        }

        return  ResponseEntity.ok().body(resultTemplate);
    }
}
