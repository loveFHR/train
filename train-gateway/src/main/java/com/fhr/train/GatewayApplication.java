package com.fhr.train;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * @author FHR
 * @create 2024/12/15 17:01
 */
@SpringBootApplication(scanBasePackages = "com.fhr.train")
public class GatewayApplication {
    private static final Logger LOG =  LoggerFactory.getLogger(GatewayApplication.class);
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(GatewayApplication.class);
        ConfigurableEnvironment environment = app.run(args).getEnvironment();
        LOG.info("网关项目启动成功");
        LOG.info("接口地址: \thttp://localhost:{}/doc.html", environment.getProperty("server.port"));
    }
}