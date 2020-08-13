package com.lambda.crudyorders.controllers;


import com.lambda.crudyorders.models.Order;
import com.lambda.crudyorders.services.OrderServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;


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

    // http://localhost:2019/orders/order
    @PostMapping(value = "/order", consumes = "application/json")
    public ResponseEntity<?> postNewOrder(@Valid @RequestBody Order newOrder) {
        newOrder.setOrdnum(0);
        newOrder = orderServices.save(newOrder);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newOrderURI = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/" + newOrder.getOrdnum())
                .build().toUri();
        responseHeaders.setLocation(newOrderURI);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

    //http://localhost:2019/orders/order/{id}
    @PutMapping(value = "/order/{ordnum}", consumes = {"application/json"})
    public ResponseEntity<?> updateOrder (@Valid @RequestBody Order order, @PathVariable long ordnum) {
        order.setOrdnum(ordnum);
        orderServices.save(order);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "order/{ordnum}")
    public ResponseEntity<?> deleteCustomerById(@PathVariable long ordnum) {
        orderServices.delete(ordnum);
        return new ResponseEntity<>(HttpStatus.OK);
    }









}
