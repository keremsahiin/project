package com.demo.demo.dtos;

import lombok.*;

@Data
@Getter
@Setter
@Builder
@RequiredArgsConstructor
public class EntryDto {
    ProductDto product;
    Integer quantity;
}
