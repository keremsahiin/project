package com.demo.demo.controller;

import com.demo.demo.dtos.CustomerDto;
import com.demo.demo.service.CustomerService;
import jakarta.annotation.Resource;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Resource
    private CustomerService customerService;

    @Resource
    private ModelMapper modelMapper;

    @PostMapping("/addCustomer")
    public ResponseEntity addCustomer(@RequestBody CustomerDto customerDto){
        try {
            customerService.addCustomer(customerDto);
            return new ResponseEntity<>("Customer Successfully added", HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }

    }

}
