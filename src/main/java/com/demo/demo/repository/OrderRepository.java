package com.demo.demo.repository;

import com.demo.demo.dtos.OrderDto;
import com.demo.demo.entity.Customer;
import com.demo.demo.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> getOrderById(Long id);

    List<Order> getAllOrdersByCustomer(Customer customer);
}
