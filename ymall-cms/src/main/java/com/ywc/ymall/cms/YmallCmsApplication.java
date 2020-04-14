package com.ywc.ymall.cms;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@EnableDubbo
@MapperScan("com.ywc.ymall.cms.mapper")
@SpringBootApplication
public class YmallCmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(YmallCmsApplication.class, args);
    }

}
