package ru.web.laba_web2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaAuditing
@EnableJpaRepositories
public class LabaWeb2Application {

    public static void main(String[] args) {
        SpringApplication.run(LabaWeb2Application.class, args);
    }

}
