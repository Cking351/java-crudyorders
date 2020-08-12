package com.lambda.crudyorders.controllers;


import com.lambda.crudyorders.models.Order;
import com.lambda.crudyorders.services.OrderServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderServices orderServices;

    // http://localhost:2019/orders/order/{ordernumber}
    @GetMapping(value = "/order/{ordernum}", produces = "application/json")
    public ResponseEntity<?> findOrderById(@PathVariable long ordernum) {
        Order myList = orderServices.findOrderNumById(ordernum);
        return new ResponseEntity<>(myList, HttpStatus.OK);
    }


}
