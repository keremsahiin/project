package com.demo.demo.service;

import com.demo.demo.entity.Cart;
import com.demo.demo.entity.Customer;
import com.demo.demo.entity.Product;

public interface CartService {

    Cart getCartForCustomer(Customer customer) throws Exception;

    void addProductToCart(String productCode, int quantity) throws Exception;


    void removeProductFromCart(String productCode) throws Exception;

    void updateCart(String productCode , int quantity) throws Exception;

    void emptyCart() throws Exception;

    void calculateCart(Cart cart) throws Exception;

}
