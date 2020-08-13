package com.lambda.crudyorders.repositories;


import com.lambda.crudyorders.models.Agent;
import org.springframework.data.repository.CrudRepository;

public interface AgentRepository extends CrudRepository<Agent, Long> {
}
