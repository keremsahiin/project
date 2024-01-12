package com.demo.demo.dtos;

import lombok.*;

import java.util.Set;
@Data
@Getter
@Setter
@Builder
@RequiredArgsConstructor
public class OrderDto {
    String id;
    Set<EntryDto> entries;
    Double totalPriceOfProducts;
    Double totalPrice;
}
