package com.lambda.crudyorders.controllers;


import com.lambda.crudyorders.models.Customer;
import com.lambda.crudyorders.services.CustomerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
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

    // http://localhost:2019/customers/customer
    @PostMapping(value = "/customer", consumes = "application/json")
    public ResponseEntity<?> addNewCustomer(@Valid @RequestBody Customer newCustomer) {
        newCustomer.setCustcode(0);
        newCustomer = customerServices.save(newCustomer);


        HttpHeaders responseHeaders = new HttpHeaders();
        URI newCustomerURI = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/" + newCustomer.getCustcode()).build().toUri();
        responseHeaders.setLocation(newCustomerURI);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

    // http://localhost:2019/customers/customer/{ID}
    @PutMapping(value = "/customer/{custid}", consumes = "application/json")
    public ResponseEntity<?> updateFullCustomer(@Valid @RequestBody Customer updateCustomer, @PathVariable long custid) {
        updateCustomer.setCustcode(custid);
        customerServices.save(updateCustomer);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    // http://localhost:2019/customers/customer/19
    @PatchMapping(value = "/customer/{custid}", consumes = "application/json")
    public ResponseEntity<?> updateCustomer(@RequestBody Customer updateCustomer, @PathVariable long custid) {
        customerServices.update(updateCustomer, custid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // http://localhost:2019/customers/customer/54
    @DeleteMapping(value = "customer/{custid}")
    public ResponseEntity<?> deleteCustomerById(@PathVariable long custid) {
        customerServices.delete(custid);
        return new ResponseEntity<>(HttpStatus.OK);
    }











}
