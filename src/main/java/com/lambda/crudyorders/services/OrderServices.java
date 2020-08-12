package com.lambda.crudyorders.services;


import com.lambda.crudyorders.models.Order;

import java.util.List;

public interface OrderServices {

    Order findOrderNumById(long ordernum);

    List<Order> findAdvanceAmount();

    Order save(Order order);
}
