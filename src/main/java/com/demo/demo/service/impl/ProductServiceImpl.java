package com.demo.demo.service.impl;

import com.demo.demo.dtos.ProductDto;
import com.demo.demo.entity.Product;
import com.demo.demo.repository.ProductRepository;
import com.demo.demo.service.ProductService;
import com.demo.demo.service.SessionService;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    @Resource
    private ProductRepository productRepository;

    @Resource
    private ModelMapper modelMapper;


    @Override
    public Product getProductForCode(String code) throws Exception {
        final Optional<Product> optionalProduct = productRepository.getProductByCode(code);
        if (optionalProduct.isPresent()) {
            return optionalProduct.get();
        } else {
            throw new Exception("Product not found for code: " + code);
        }
    }

    @Override
    public void addProduct(ProductDto productDto) {
        Product product = modelMapper.map(productDto, Product.class);
        productRepository.save(product);
    }

    @Override
    public void deleteProduct(String code) throws Exception {
        final Product product = this.getProductForCode(code);
        productRepository.delete(product);
    }

    @Override
    public void updateProduct(String code, ProductDto productDto) throws Exception {
        Product product = this.getProductForCode(code);
        if(productDto.getCode() != null){
            product.setCode(productDto.getCode());
        }
        if(productDto.getName() != null){
            product.setName(productDto.getName());
        }
        if(productDto.getStockValue() != null){
            product.setStockValue(productDto.getStockValue());
        }
        if(productDto.getPrice() != null){
            product.setPrice(productDto.getPrice());
        }
        productRepository.save(product);
    }
}
