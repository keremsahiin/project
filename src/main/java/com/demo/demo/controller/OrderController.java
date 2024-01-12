package com.demo.demo.controller;

import com.demo.demo.service.OrderService;
import jakarta.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Resource
    private OrderService orderService;

    @PostMapping("/placeOrder")
    public ResponseEntity placeOrder(){
        try {
            orderService.placeOrder();
            return new ResponseEntity<>("Successfully placing order",HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getOrder/{orderCode}")
    private ResponseEntity getOrderByCode(@PathVariable String orderCode){
       try {
           return new ResponseEntity<>(orderService.getOrderForCode(orderCode),HttpStatus.OK);
       }catch (Exception e){
           return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
       }

    }

  //  @GetMapping("/getAllOrderForCustomer/{customerCode}")

}
