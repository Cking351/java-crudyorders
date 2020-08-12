package com.lambda.crudyorders.repositories;


import com.lambda.crudyorders.models.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

    List<Customer> findByCustnameContainingIgnoringCase (String name);
}
