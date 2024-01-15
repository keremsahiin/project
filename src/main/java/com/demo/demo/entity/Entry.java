package com.demo.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "entries")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Entry extends BaseEntity {

    @OneToOne
    private Product product;

    private int quantity;

    private double entryPrice;
}
