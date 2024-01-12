package com.demo.demo.service.impl;

import com.demo.demo.entity.Cart;
import com.demo.demo.entity.Customer;
import com.demo.demo.entity.Order;
import com.demo.demo.repository.OrderRepository;
import com.demo.demo.service.OrderService;
import com.demo.demo.service.SessionService;
import jakarta.annotation.Resource;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderRepository orderRepository;

    @Resource
    private ModelMapper modelMapper;

    @Resource
    private SessionService sessionService;

    @Override
    public void placeOrder() throws Exception {
        Order order = new Order();
        Customer customer = sessionService.getCurrentCustomer();
        Cart cart = sessionService.getCurrentCart();

        order.setEntries(cart.getEntries());
        order.setCustomer(cart.getCustomer());
        order.setTotalPrice(cart.getTotalPriceOfProducts());
        order.setTotalPrice(order.getTotalPrice());
        orderRepository.save(order);
    }

    @Override
    public Order getOrderForCode(String code) throws Exception {
        final Optional<Order> orderOptional = orderRepository.getOrderId(code);
        if (orderOptional.isPresent()){
            return orderOptional.get();
        }else{
            throw new Exception("Order is not found!" + code);
        }
    }

    @Override
    public List<Order> getAllOrdersForCustomer() throws Exception {
        Customer customer = sessionService.getCurrentCustomer();
        List<Order> orders = orderRepository.getOrderByCustomer(customer);
        if

    }
}
