package com.learning.springbootdocker;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AgentController {

    private final AgentJdbcRepository repository;

    public AgentController(AgentJdbcRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/agents")
    public List<Agent> all() {
        return repository.findAll();
    }
}
