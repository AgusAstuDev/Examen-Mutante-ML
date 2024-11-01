package com.mutant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan(basePackages = "com.meli.mutant.controller")
public class MutantApp {

    public static void main(String[] args) {
        SpringApplication.run(MutantApp.class, args);
        System.out.println("-------------------------------------------");
        System.out.println("API de control de ADNs mutantes funcionando");
        System.out.println("-------------------------------------------");
    }

}
