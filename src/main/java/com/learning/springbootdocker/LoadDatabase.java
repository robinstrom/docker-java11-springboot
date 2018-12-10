package com.learning.springbootdocker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Bean
    CommandLineRunner initDatabase(AgentJdbcRepository repository) {
        return args -> {
            log.info("Preloading " + repository.insert(new Agent((long) 2, "Bilbo", "Baggins")));
            log.info("Preloading " + repository.insert(new Agent((long) 3, "Frodo", "Baggins")));
            log.info("Preloading " + repository.insert(new Agent((long) 11, "Albus", "Dumbledore")));
        };
    }
}
