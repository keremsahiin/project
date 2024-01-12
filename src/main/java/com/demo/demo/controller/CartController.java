package com.demo.demo.controller;

import com.demo.demo.entity.Customer;
import com.demo.demo.service.CartService;
import com.demo.demo.service.SessionService;
import jakarta.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Resource
    private CartService cartService;

    @Resource
    private SessionService sessionService;

    @GetMapping("/getCart")
    public ResponseEntity getCartForCustomer(){
        Customer customer = sessionService.getCurrentCustomer();
        try {
            return new ResponseEntity<>(cartService.getCartForCustomer(customer), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/addCart/{cartCode}")
    public ResponseEntity updateCart(@RequestParam String productCode, @RequestParam int quantity){
        try {
            cartService.addProductToCart(productCode,quantity);
            return new ResponseEntity<>("Successfully adding",HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/deleteCart/{cartCode}")
    public ResponseEntity deleteCart(@RequestParam String productCode ){
        try {
            cartService.removeProductFromCart(productCode);
            return new ResponseEntity<>("Successfully deleted", HttpStatus.OK);
        }catch (Exception e){
          return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    //empty eklenecek
}
