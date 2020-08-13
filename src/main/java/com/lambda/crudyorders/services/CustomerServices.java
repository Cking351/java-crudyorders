package com.lambda.crudyorders.services;


import com.lambda.crudyorders.models.Customer;

import java.util.List;

public interface CustomerServices {

    List<Customer> findAllOrders();

    Customer findOrderById(long id);

    List<Customer> findByNameLike(String name);

    Customer save(Customer customer);

    Customer update(Customer customer, long id);

    void delete(long id);
}
