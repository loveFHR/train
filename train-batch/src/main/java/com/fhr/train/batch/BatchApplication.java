package com.fhr.train.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * @author FHR
 * @create 2024/12/18 9:56
 */
@SpringBootApplication
public class BatchApplication {
    private static final Logger log = LoggerFactory.getLogger(BatchApplication.class);

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(BatchApplication.class);
        ConfigurableEnvironment environment = app.run(args).getEnvironment();
        log.info("项目启动成功");
        log.info("接口地址：\thttp://localhost:{}{}/doc.html",
                environment.getProperty("server.port"),
                environment.getProperty("server.servlet.context-path"));
    }
}