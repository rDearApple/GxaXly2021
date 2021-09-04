package com.gxa.xly2021;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.gxa.xly2021.dao")
public class Xly2021Application {

    public static void main(String[] args) {
        SpringApplication.run(Xly2021Application.class, args);
    }

}
