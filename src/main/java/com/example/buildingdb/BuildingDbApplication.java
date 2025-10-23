package com.example.buildingdb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy(proxyTargetClass = true)
@SpringBootApplication
public class BuildingDbApplication {

    public static void main(String[] args) {
        SpringApplication.run(BuildingDbApplication.class, args);
    }

}
