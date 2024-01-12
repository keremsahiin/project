package com.demo.demo.dtos;

import lombok.*;

@Data
@Getter
@Setter
@Builder
@RequiredArgsConstructor
public class ProductDto {
    String code;
    String name;
    Integer stockValue;
    Double price;
}
