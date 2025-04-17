package com.example.mcpservermvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.example.mcpservermvc.client")
public class McpServerMvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(McpServerMvcApplication.class, args);
    }

}
