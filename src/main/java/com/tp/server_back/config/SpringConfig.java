package com.tp.server_back.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by nico on 22/02/18.
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.tp.server_back"})
@EnableJpaRepositories("com.tp.server_back.repository")
@EnableAutoConfiguration
@EnableTransactionManagement
@EntityScan(basePackages = {"com.tp.serve_back.entities"})
//@Import(value = {WebSecurityConfig.class})
public class SpringConfig {
    public static void main(String[] args) {

        SpringApplication.run(SpringConfig.class, args);
    }
}
