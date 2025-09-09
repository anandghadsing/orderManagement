package com.example.oms.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter @Setter
@Table(name="orders")
public class Order {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank private String customerEmail;
    @NotBlank private String productSku;

    @Min(1) private int quantity;

    @NotBlank
    @Column(length = 20)
    private String status;

    @NotNull
    private LocalDate orderDate;
}
