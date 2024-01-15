package com.demo.demo.service.impl;

import com.demo.demo.dtos.CustomerDto;
import com.demo.demo.entity.Customer;
import com.demo.demo.repository.CustomerRepository;
import com.demo.demo.service.CustomerService;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.modelmapper.*;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    @Resource
    private CustomerRepository customerRepository;
    @Resource
    private ModelMapper modelMapper;

    // TODO: Inversion Of Control (IoC), Dependency Injection, Ioc Container (Springte ApplicationContext) -> bunlar çok önemli
    //  Bean, Scope, Filter -> yüzeysel araştır
    //  Bean vs Java Objesi farkını araştır.
    //  resource vs autowired arası fark
    public Boolean isCustomerExists(String username) {
        Optional<Customer> customerByUsername = customerRepository.getCustomerByUsername(username);
        return customerByUsername.isPresent();
    }

    public void addCustomer(CustomerDto customerDto) throws Exception {
        Optional<Customer> optionalCustomer = customerRepository.getCustomerByUsername(customerDto.getUsername());
        if(optionalCustomer.isPresent()){
            throw new Exception("Customer is not found");
        }
        Customer customer = modelMapper.map(customerDto, Customer.class);
        customerRepository.save(customer);
    }

    @Override
    public Customer findCustomerByUsername(String username) throws Exception {
        final Optional<Customer> optionalCustomer = customerRepository.getCustomerByUsername(username);
        if (optionalCustomer.isPresent()) {
            return optionalCustomer.get();
        }
        throw new Exception("Customer is not found with username: " + username);
    }

    public boolean updateCustomer(CustomerDto customerDto) throws Exception {
        final Customer customerByUsername = findCustomerByUsername(customerDto.getUsername());
        customerByUsername.setFirstName(customerDto.getFirstName());
        customerByUsername.setLastName(customerDto.getLastName());
        customerRepository.save(customerByUsername);
        return true;
    }
}
