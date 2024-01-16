package com.demo.demo.filter;

import com.demo.demo.entity.Cart;
import com.demo.demo.entity.Customer;
import com.demo.demo.service.CartService;
import com.demo.demo.service.CustomerService;
import com.demo.demo.service.SessionService;
import jakarta.annotation.Resource;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SessionFilter extends OncePerRequestFilter {

    @Resource
    private SessionService sessionService;

    @Resource
    private CustomerService customerService;

    @Resource
    private CartService cartService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String customer = request.getHeader("customer");
        try {
            final Customer customerByUsername = customerService.findCustomerByUsername(customer);
            sessionService.setCurrentCustomer(customerByUsername);
            final Cart cartForCustomer = cartService.getCartForCustomer(customerByUsername);
            sessionService.setCurrentCart(cartForCustomer);
        } catch (Exception ex) {

        }
        filterChain.doFilter(request, response);
    }


}
