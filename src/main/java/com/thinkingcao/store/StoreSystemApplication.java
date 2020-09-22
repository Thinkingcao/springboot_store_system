package com.thinkingcao.store;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @MapperScan: 扫描Mapper文件夹
 */
@SpringBootApplication
@MapperScan(basePackages = {"com.thinkingcao.store.system.mapper"})
public class StoreSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(StoreSystemApplication.class, args);
    }



}
