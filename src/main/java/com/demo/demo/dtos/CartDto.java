package com.demo.demo.dtos;

import lombok.*;
import java.util.Set;

@Data
public class CartDto {
    Set<EntryDto> entries;
    Double totalPriceOfProducts;
    Integer numberOfProducts;
}
