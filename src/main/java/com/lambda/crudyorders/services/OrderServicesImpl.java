package com.lambda.crudyorders.services;


import com.lambda.crudyorders.models.Order;
import com.lambda.crudyorders.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service(value = "orderServices")
public class OrderServicesImpl implements OrderServices{

    @Autowired
    OrderRepository orderrepos;

    @Override
    public Order findOrderNumById(long ordernum) {
        return orderrepos.findById(ordernum).orElseThrow(() -> new EntityNotFoundException("Order " + ordernum + "Not Found!"));
    }

    @Override
    public List<Order> findAdvanceAmount() {
        return null;
    }

    @Override
    public Order save(Order order) {
        return orderrepos.save(order);
    }
}
