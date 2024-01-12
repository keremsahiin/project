package com.demo.demo.service;

import com.demo.demo.entity.Cart;
import com.demo.demo.entity.Customer;

public interface CartService {

    Cart getCartForCustomer(Customer customer) throws Exception;

    boolean addProductToCart(String productCode, int quantity) throws Exception;


    boolean removeProductFromCart(String productCode) throws Exception;

    void updateCart(String productCode , int quantity) throws Exception;

    boolean emptyCart();

    void calculateCart(Cart cart) throws Exception;

}
