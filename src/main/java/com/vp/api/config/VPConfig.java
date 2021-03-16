package com.vp.api.config;

import com.vp.api.controller.MoviesController;
import com.vp.api.repository.MoviesRepository;
import com.vp.api.service.MoviesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@PropertySource("classpath:/application.properties")
@EnableMongoRepositories({ "com.vp.api.repository" })
public class VPConfig {

    @Bean
    public MoviesController controller(){
        return new MoviesController(service());
    }

    @Bean
    public MoviesService service(){
        return new MoviesService();
    }

}
