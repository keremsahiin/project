package com.demo.demo.repository;

import com.demo.demo.entity.Cart;
import com.demo.demo.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findCartByCustomer(Customer customer);

}
