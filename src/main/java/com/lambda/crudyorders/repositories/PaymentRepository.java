package com.lambda.crudyorders.repositories;


import com.lambda.crudyorders.models.Payment;
import org.springframework.data.repository.CrudRepository;

public interface PaymentRepository extends CrudRepository<Payment, Long> {
}
