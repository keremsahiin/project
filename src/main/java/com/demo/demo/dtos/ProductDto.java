package com.demo.demo.dtos;

import lombok.*;

@Data
public class ProductDto {
    String code;
    String name;
    Integer stockValue;
    Double price;
}
