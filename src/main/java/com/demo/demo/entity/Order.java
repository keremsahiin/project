package com.demo.demo.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Order extends BaseEntity{

    @OneToMany(fetch = FetchType.EAGER)
    @Nullable
    private Set<Entry> entries;

    private Double totalPriceOfProducts;

    private Double totalPrice;

    @ManyToOne
    private Customer customer;
}
