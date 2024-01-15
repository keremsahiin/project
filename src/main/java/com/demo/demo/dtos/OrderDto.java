package com.demo.demo.dtos;

import lombok.*;

import java.util.Set;
@Data
public class OrderDto {
    String code;

    Set<EntryDto> entries;

    Double totalPriceOfProducts;

    Double totalPrice;

}
