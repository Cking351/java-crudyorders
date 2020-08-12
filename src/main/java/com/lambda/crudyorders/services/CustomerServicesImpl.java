package com.lambda.crudyorders.services;


import com.lambda.crudyorders.models.Customer;
import com.lambda.crudyorders.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service(value = "customerServices")
public class CustomerServicesImpl implements CustomerServices {
    @Autowired
    CustomerRepository custrepos;

    @Override
    public List<Customer> findAllOrders() {
        List<Customer> list = new ArrayList<>();
        custrepos.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public Customer findOrderById(long id) {
        return custrepos.findById(id).orElseThrow(() -> new EntityNotFoundException("Order " + id + "Not Found!"));
    }

    @Override
    public List<Customer> findByNameLike(String name) {
         ArrayList<Customer> customerList = (ArrayList<Customer>) custrepos.findByCustnameContainingIgnoringCase(name);
         return customerList;
    }

    @Transactional
    @Override
    public Customer save(Customer customer) {
        return custrepos.save(customer);
    }
}
