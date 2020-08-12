package com.lambda.crudyorders.controllers;


import com.lambda.crudyorders.models.Customer;
import com.lambda.crudyorders.services.CustomerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private CustomerServices customerServices;

    // http://localhost:2019/customers/orders
    @GetMapping(value = "/orders", produces = "application/json")
    public ResponseEntity<?> listAllOrders() {
        List<Customer> myList = customerServices.findAllOrders();
        return new ResponseEntity<>(myList, HttpStatus.OK); // <- THIS IS WORKING NOW!!
    }





    // http://localhost:2019/customers/{ordernumber}
    @GetMapping(value = "/{ordernumber}", produces = "application/json")
    public ResponseEntity<?> findOrderById(@PathVariable long ordernumber) {
        Customer myCustomer = customerServices.findOrderById(ordernumber);
        return new ResponseEntity<>(myCustomer, HttpStatus.OK);
    }



    // http://localhost:2019/customers/namelike/{namelike}
    @GetMapping(value = "/namelike/{name}", produces = "application/json")
    public ResponseEntity<?> findAllCustomersByNameLike(@PathVariable String name) {
        List<Customer> myList = customerServices.findByNameLike(name);
        return new ResponseEntity<>(myList, HttpStatus.OK);
    }


}
