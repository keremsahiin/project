package com.demo.demo.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;


@Table(name = "carts")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Cart extends BaseEntity{

    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(unique = true)
    private String code;

    @OneToMany(fetch = FetchType.EAGER)
    @Nullable
    private Set<Entry> entries;

    private Double totalPriceOfProducts;

    @OneToOne
    private Customer customer;
}
