package com.jianzheng.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * SpringBoot启动类
 *
 * @author tanghuang 2020年02月20日
 */
@EnableTransactionManagement
@SpringBootApplication
public class MainServerApplication {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(MainServerApplication.class);
        springApplication.run(args);
    }

}
