package com.jpmc.theater;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by ryee on 4/7/23
 */
@SpringBootApplication
public class MovieTheaterApp {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(MovieTheaterApp.class);
        application.setWebApplicationType(WebApplicationType.NONE);
        application.run(args);
    }
}
