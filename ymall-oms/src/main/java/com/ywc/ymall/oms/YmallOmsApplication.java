package com.ywc.ymall.oms;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@EnableDubbo
@MapperScan("com.ywc.ymall.oms.mapper")
@SpringBootApplication
public class YmallOmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(YmallOmsApplication.class, args);
    }

}
