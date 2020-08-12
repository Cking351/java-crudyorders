package com.lambda.crudyorders.services;


import com.lambda.crudyorders.models.Agent;

public interface AgentServices {

    Agent findAgentsById(long id);


    Agent save(Agent agent);

}
