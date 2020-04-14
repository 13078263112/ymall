package com.ywc.ymall.ums;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@EnableDubbo
@MapperScan("com.ywc.ymall.ums.mapper")
@SpringBootApplication
public class YmallUmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(YmallUmsApplication.class, args);
    }

}
