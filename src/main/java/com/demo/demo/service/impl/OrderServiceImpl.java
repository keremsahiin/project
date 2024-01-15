package com.demo.demo.service.impl;

import com.demo.demo.dtos.EntryDto;
import com.demo.demo.dtos.OrderDto;
import com.demo.demo.entity.Cart;
import com.demo.demo.entity.Customer;
import com.demo.demo.entity.Order;
import com.demo.demo.repository.OrderRepository;
import com.demo.demo.service.OrderService;
import com.demo.demo.service.SessionService;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
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
        final Optional<Order> orderOptional = orderRepository.getOrderById(Long.parseLong(code));
        if (orderOptional.isPresent()) {
            return orderOptional.get();
        } else {
            throw new Exception("Order is not found!" + code);
        }
    }

    @Override
    public List<OrderDto> getAllOrdersForCustomer() throws Exception {
        Customer customer = sessionService.getCurrentCustomer();
        List<Order> orders = orderRepository.getAllOrdersByCustomer(customer);
        List<OrderDto> orderDtos = new LinkedList<>();
        if (CollectionUtils.isEmpty(orders)) {
            return Collections.emptyList();
        } else {
            for (Order order : orders) {
                OrderDto orderDto = new OrderDto();
                Set<EntryDto> entries = order.getEntries()
                        .stream()
                        .map(entryDto -> modelMapper.map(entryDto , EntryDto.class))
                        .collect(Collectors.toSet());
                orderDto.setEntries(entries);
                orderDto.setCode(String.valueOf(order.getId()));
                orderDto.setTotalPrice(order.getTotalPrice());
                orderDto.setTotalPriceOfProducts(order.getTotalPriceOfProducts());
                orderDtos.add(orderDto);
            }
            return orderDtos;
        }
    }
}
