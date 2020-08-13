package com.lambda.crudyorders.controllers;


import com.lambda.crudyorders.models.Agent;
import com.lambda.crudyorders.services.AgentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/agents")
public class AgentController {
    @Autowired
    private AgentServices agentServices;

    // http://localhost:2019/agents/agent{agentid}
    @GetMapping(value = "/agent/{id}", produces = "application/json")
    public ResponseEntity<?> findAgentsById(@PathVariable long id) {
        Agent myList = agentServices.findAgentsById(id);
        return new ResponseEntity<>(myList, HttpStatus.OK);
    }
}
