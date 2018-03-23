package com.tp.server_back.config;

import com.tp.server_back.entities.Data;
import com.tp.server_back.services.DataService;
import com.tp.server_back.services.utils.FileParsingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.io.IOException;

/**
 * Created by nico on 22/02/18.
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.tp.server_back"})
@EnableJpaRepositories("com.tp.server_back.repository")
@EnableAutoConfiguration
@EntityScan(basePackages = {"com.tp.server_back.entities"})
public class SpringConfig {

    @Autowired
    DataService dataService;

    public static void main(String[] args) throws IOException {

        SpringApplication.run(SpringConfig.class, args);


    }

    public void saveData() throws IOException{
        Data data = new Data();

        this.dataService.save(data);
    }
}
