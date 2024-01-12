package com.demo.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "customers")
public class Customer extends BaseEntity {

    @Column(unique = true)
    private String username;

    private String firstName;

    private String lastName;

}
