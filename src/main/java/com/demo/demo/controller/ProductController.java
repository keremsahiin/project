package com.demo.demo.controller;

import com.demo.demo.dtos.ProductDto;
import com.demo.demo.entity.Product;
import com.demo.demo.repository.ProductRepository;
import com.demo.demo.service.CartService;
import com.demo.demo.service.ProductService;
import jakarta.annotation.Resource;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Resource
    private ProductService productService;

    @Resource
    private ModelMapper modelMapper;

    @Resource
    private CartService cartService;


    @GetMapping("/getProduct/{productCode}")
    public ResponseEntity getProductByCode(@PathVariable String productCode){
        try{
            Product product = productService.getProductForCode(productCode);
            return new ResponseEntity<>(modelMapper.map(product, ProductRepository.class),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/addProduct")
    public ResponseEntity addProduct(@RequestBody ProductDto productDto){
        try {
            productService.addProduct(productDto);
            return new ResponseEntity<>("Product successfully added",HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/updateProduct/{productCode}")
    public ResponseEntity updateProduct(@PathVariable String productCode , @RequestBody ProductDto productDto){
        try {
            productService.updateProduct(productCode, productDto);
            return new ResponseEntity<>("Product updated",HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deleteProduct/{productCode}")
    public ResponseEntity deleteProduct(@PathVariable String productCode){
        try {
            productService.deleteProduct(productCode);
            return new ResponseEntity<>("Product deleted",HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/add-Product-To-Cart/{productCode}")
    public ResponseEntity addProductToCart(@RequestParam String productCode, @RequestParam Integer qty) {
        try {
            cartService.addProductToCart(productCode,qty);
            return new ResponseEntity<>("Added product to cart", HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping("/remove-Product-To-Cart/{productCode}")
    public ResponseEntity removeProductFromCart(@RequestParam String productCode) {
        try {
            cartService.removeProductFromCart(productCode);
            return new ResponseEntity<>("Deleted product to cart", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


}
