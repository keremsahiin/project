package com.demo.demo.service;

import com.demo.demo.dtos.OrderDto;
import com.demo.demo.entity.Order;

import java.util.List;

public interface OrderService {
    public void placeOrder() throws Exception;

    Order getOrderForCode(String code) throws Exception;

    public List<OrderDto> getAllOrdersForCustomer() throws Exception;
}
