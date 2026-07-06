package com.home.subms;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProfileMainStarter implements CommandLineRunner {


    public static void main(String[] args) {
        SpringApplication.run(ProfileMainStarter.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("ProfileMainStarter - Application started..");
    }
}