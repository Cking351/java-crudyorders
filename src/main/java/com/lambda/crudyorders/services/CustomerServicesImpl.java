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


    @Override
    public Customer update(Customer customer, long id) {
        Customer currentCustomer = custrepos.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer " + id + " Not Found!"));


        // VALIDATION FOR BASE FIELDS
        if (customer.getCustname() != null) {
            currentCustomer.setCustname(customer.getCustname());
        }
        if (customer.getCustcity() != null) {
            currentCustomer.setCustcity(customer.getCustcity());
        }
        if (customer.getCustcountry() != null) {
            currentCustomer.setCustcountry(customer.getCustcountry());
        }
        if (customer.getWorkingarea() != null) {
            currentCustomer.setWorkingarea(customer.getWorkingarea());
        }
        if (customer.getGrade() != null) {
            currentCustomer.setGrade(customer.getGrade());
        }
        if (customer.hasvalueforopeningamt) {
            currentCustomer.setOpeningamt(customer.getOpeningamt());
        }
        if (customer.hasvalueforoutstandingamt) {
            currentCustomer.setOutstandingamt(customer.getOutstandingamt());
        }
        if (customer.hasvalueforpaymentamt) {
            currentCustomer.setPaymentamt(customer.getPaymentamt());
        }
        if (customer.getAgent() != null) {
            currentCustomer.setAgent(customer.getAgent());
        }
        if (customer.getPhone() != null) {
            currentCustomer.setPhone(customer.getPhone());
        }
        if (customer.hasvalueforreceiveamt) {
            currentCustomer.setReceiveamt(customer.getReceiveamt());
        }

        currentCustomer.getOrders().clear();
        for (Order o : customer.getOrders()) {
            Order newOrder = new Order(o.getOrdamount(), o.getAdvanceamount(), currentCustomer, o.getOrderdescription());
            newOrder.setPayments(o.getPayments());
            currentCustomer.getOrders().add(newOrder);
        }
        return custrepos.save(currentCustomer);
    }

    @Transactional
    @Override
    public void delete(long id) {
        if (custrepos.findById(id).isPresent()) {
            custrepos.deleteById(id);
        } else {
            throw new EntityNotFoundException("Customer " + id + " Not Found!");
        }
    }
}
