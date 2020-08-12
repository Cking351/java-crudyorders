package com.lambda.crudyorders.services;


import com.lambda.crudyorders.models.Agent;
import com.lambda.crudyorders.repositories.AgentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@Service(value = "agentService")
public class AgentServiceImpl implements AgentServices {
    @Autowired
    private AgentRepository agentrepos;

    @Override
    public Agent findAgentsById(long id) {
        return agentrepos.findById(id).orElseThrow(() -> new EntityNotFoundException("Agent " + id + "Not Found!"));
    }

    @Transactional
    @Override
    public Agent save(Agent agent) {
        return agentrepos.save(agent);
    }
}