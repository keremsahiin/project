package com.demo.demo.dtos;

import lombok.*;
import java.util.Set;

@Data
@Getter
@Setter
@Builder
@RequiredArgsConstructor
public class CartDto {
    Set<EntryDto> entries;
    Double totalPriceOfProducts;
    Integer numberOfProducts;
}
