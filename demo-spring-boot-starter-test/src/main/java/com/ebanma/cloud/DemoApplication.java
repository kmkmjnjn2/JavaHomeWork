package com.ebanma.cloud;

import com.ebanma.cloud.annotation.EnableRegisterServer;
import com.ebanma.cloud.bean.SimpleBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}