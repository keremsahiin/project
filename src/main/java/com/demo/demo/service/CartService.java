package com.demo.demo.service;

import com.demo.demo.entity.Cart;
import com.demo.demo.entity.Customer;

public interface CartService {

    Cart getCartForCustomer(Customer customer);

    boolean addProductToCart(String productCode, int quantity) throws Exception;


    boolean removeProductFromCart(String productCode) throws Exception;

    //empty eklenecek

}
