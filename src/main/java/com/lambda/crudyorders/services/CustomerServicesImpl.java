package com.lambda.crudyorders.services;


import com.lambda.crudyorders.models.Customer;
import com.lambda.crudyorders.models.Order;
import com.lambda.crudyorders.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Transactional
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
        Customer newCustomer = new Customer();

        if (customer.getCustcode() != 0) {
            custrepos.findById(customer.getCustcode())
                    .orElseThrow(() -> new EntityNotFoundException("Customer " + customer.getCustcode() + "Not Found!"));
            newCustomer.setCustcode(customer.getCustcode());
        }

        // VALIDATION FOR BASE FIELDS
        newCustomer.setCustname(customer.getCustname());
        newCustomer.setCustcity(customer.getCustcity());
        newCustomer.setCustcountry(customer.getCustcountry());
        newCustomer.setWorkingarea(customer.getWorkingarea());
        newCustomer.setGrade(customer.getGrade());
        newCustomer.setOpeningamt(customer.getOpeningamt());
        newCustomer.setOutstandingamt(customer.getOutstandingamt());
        newCustomer.setPaymentamt(customer.getPaymentamt());
        newCustomer.setAgent(customer.getAgent());
        newCustomer.setPhone(customer.getPhone());
        newCustomer.setReceiveamt(customer.getReceiveamt());

        newCustomer.getOrders().clear();
        for (Order o : customer.getOrders()) {
            Order newOrder = new Order(o.getOrdamount(), o.getAdvanceamount(), newCustomer, o.getOrderdescription());
            newOrder.setPayments(o.getPayments());
            newCustomer.getOrders().add(newOrder);
        }



        return custrepos.save(newCustomer);
    }
}
