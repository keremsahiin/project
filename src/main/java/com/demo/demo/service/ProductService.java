package com.demo.demo.service;

import com.demo.demo.dtos.ProductDto;
import com.demo.demo.entity.Product;
import com.demo.demo.repository.ProductRepository;

public interface ProductService {

    Product getProductForCode(String code) throws Exception;

    void addProduct(ProductDto productDto);

    void deleteProduct(String code) throws Exception;

    void updateProduct(String code, ProductDto productDto) throws Exception;

}
