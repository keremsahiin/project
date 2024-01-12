package com.demo.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "entries")
public class Entry {

    @OneToOne
    private Product product;

    private int quantity;

    private double entryPrice;
}
