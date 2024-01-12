package com.demo.demo.service;

import com.demo.demo.dtos.CustomerDto;
import com.demo.demo.entity.Customer;

public interface CustomerService {
    Boolean isCustomerExists(String username);
    boolean addCustomer(CustomerDto customerDto) throws Exception;

    Customer findCustomerByUsername(String username) throws Exception;
}
