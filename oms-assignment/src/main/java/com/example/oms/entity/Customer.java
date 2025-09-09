package com.example.oms.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name = "customers", uniqueConstraints = @UniqueConstraint(columnNames = {"email"}))
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message="Customer name is required")
    private String name;

    @Email @NotBlank
    private String email;

    @NotBlank
    private String phone;

    private String address;
}
