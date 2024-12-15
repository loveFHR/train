package com.fhr.train.member;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * @author FHR
 * @create 2024/12/15 14:51
 */
@SpringBootApplication(scanBasePackages = "com.fhr.train")
public class MemberApplication {
   private static final Logger LOG =  LoggerFactory.getLogger(MemberApplication.class);
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(MemberApplication.class);
        ConfigurableEnvironment environment = app.run(args).getEnvironment();
        LOG.info("会员项目启动成功");
        LOG.info("接口地址: \thttp://localhost:{}/doc.html", environment.getProperty("server.port"));
    }
}