package com.px.seckill_px;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.px.seckill_px.db.mappers")
@ComponentScan(basePackages = {"com.px"})
public class SeckillPxApplication {

    public static void main(String[] args) {
        SpringApplication.run(SeckillPxApplication.class, args);
    }

}
