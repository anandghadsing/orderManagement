package com.example.oms.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name="products", uniqueConstraints = @UniqueConstraint(columnNames = {"sku"}))
public class Product {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank private String sku;
    @NotBlank private String name;
    @PositiveOrZero private Double price;
}
