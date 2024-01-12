package com.demo.demo.service.impl;

import com.demo.demo.entity.Cart;
import com.demo.demo.entity.Customer;
import com.demo.demo.entity.Order;
import com.demo.demo.repository.OrderRepository;
import com.demo.demo.service.OrderService;
import com.demo.demo.service.SessionService;
import jakarta.annotation.Resource;
import org.modelmapper.ModelMapper;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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
        List<Order> orders = orderRepository.getAllOrderByCustomer(customer);
        if(CollectionUtils.isEmpty(orders)){
            return null;
        }else {
            for (Order order : orders){
                Set<Order> orders1 = order.getEntries()
                        .stream()
                        .map(orders2 -> modelMapper.map(orders2, Order.class))
                        .collect(Collectors.toSet());
                orderRepository.getOrderByCustomer(customer);
                orders.add(order);
            }
        }
        return null;
    }
}
