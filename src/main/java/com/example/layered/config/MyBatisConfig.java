package com.example.layered.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.example.layered.**.mapper")
public class MyBatisConfig {
    
}