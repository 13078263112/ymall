package com.ywc.ymall.sms;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan("com.ywc.ymall.sms.mapper")
@EnableDubbo
@SpringBootApplication
public class YmallSmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(YmallSmsApplication.class, args);
    }

}
