package com.lou.cloud_office_back;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan({"com.lou.cloud_office_back.mapper"})
public class CloudOfficeBackApplication {

    public static void main(String[] args) {
//        SpringApplication.run(CloudOfficeBackApplication.class, args);
        SpringApplication application=new SpringApplication(CloudOfficeBackApplication.class);
        application.setAllowCircularReferences(true);
        application.run(args);
    }

}
