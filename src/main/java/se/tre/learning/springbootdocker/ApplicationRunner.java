package se.tre.learning.springbootdocker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApplicationRunner {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    AgentJdbcRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(ApplicationRunner.class, args);
    }

    public void run(String... args) throws Exception {

        logger.info("Agent id 1 -> {}", repository.findById(1));

        logger.info("Inserting -> {}", repository.insert(new Agent("John", "Bauer")));

        logger.info("Update 3 -> {}", repository.update(new Agent("FirstName-Updated", "LastName-Updated")));

        repository.deleteById(3);

        logger.info("All users -> {}", repository.findAll());
    }

}

