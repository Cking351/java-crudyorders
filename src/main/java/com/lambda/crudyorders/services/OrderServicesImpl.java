package com.lambda.crudyorders.services;


import com.lambda.crudyorders.models.Order;
import com.lambda.crudyorders.models.Payment;
import com.lambda.crudyorders.repositories.OrderRepository;
import com.lambda.crudyorders.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Transactional
@Service(value = "orderServices")
public class OrderServicesImpl implements OrderServices{

    @Autowired
    OrderRepository orderrepos;

    @Autowired
    PaymentRepository payrepos;

    @Override
    public Order findOrderNumById(long ordernum) {
        return orderrepos.findById(ordernum).orElseThrow(() -> new EntityNotFoundException("Order " + ordernum + "Not Found!"));
    }

    @Override
    public List<Order> findAdvanceAmount() {
        return null;
    }

    @Transactional
    @Override
    public Order save(Order order) {
        Order newOrder = new Order();
        if (order.getOrdnum() != 0) {

            orderrepos.findById(order.getOrdnum())
                    .orElseThrow(() -> new EntityNotFoundException("Order " + order.getOrdnum() + " Not Found!"));
            newOrder.setOrdnum(order.getOrdnum());
        }

        newOrder.setOrdamount(order.getOrdamount());
        newOrder.setCustomer(order.getCustomer());
        newOrder.setAdvanceamount(order.getAdvanceamount());
        newOrder.setOrderdescription(order.getOrderdescription());

        newOrder.getPayments().clear();
        for (Payment p : order.getPayments()) {
            Payment newPay = payrepos.findById(p.getPaymentid())
                    .orElseThrow(() -> new EntityNotFoundException("Payment " + p.getPaymentid() + " Not Found!"));

            newOrder.addPayments(newPay);
        }



        return orderrepos.save(newOrder);
    }

    @Transactional
    @Override
    public void delete(long id) {
        if (orderrepos.findById(id).isPresent()) {
            orderrepos.deleteById(id);
        } else {
            throw new EntityNotFoundException("Customer " + id + " Not Found!");
        }
    }
}
