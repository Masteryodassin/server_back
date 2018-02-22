package com.tp.server_back.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by nico on 22/02/18.
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.tp.server_back"})
@EnableJpaRepositories("com.tp.server_back.repository")
@EnableAutoConfiguration
@EntityScan(basePackages = {"com.tp.server_back.entities"})
public class SpringConfig {
    public static void main(String[] args) {

        SpringApplication.run(SpringConfig.class, args);
    }
}
