package com.unibuc.shop.repository;

import com.unibuc.shop.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByDate(String date);
    List<Order> findByCustomerNumber_CustomerId(long customerId);
}
