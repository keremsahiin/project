package com.demo.demo.dtos;

import com.demo.demo.entity.Order;
import lombok.*;

import java.util.List;
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
