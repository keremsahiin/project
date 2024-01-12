package com.demo.demo.dtos;

import lombok.*;

@Data
@Getter
@Setter
@Builder
@RequiredArgsConstructor
public class CustomerDto {
    private String username;
    private String firstName;
    private String lastName;
}
