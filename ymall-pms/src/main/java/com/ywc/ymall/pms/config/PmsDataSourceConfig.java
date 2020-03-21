package com.ywc.ymall.pms.config;

import io.shardingjdbc.core.api.MasterSlaveDataSourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

/**
 * @author 嘟嘟~
 * @date 2020/3/21 16:25
 */
@Configuration
public class PmsDataSourceConfig {
    @Bean
    public DataSource dataSource() throws IOException, SQLException {
        File file = ResourceUtils.getFile("classpath:sharding-jdbc.yml");
        return MasterSlaveDataSourceFactory.createDataSource(file);
    }
}
