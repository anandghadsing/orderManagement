package com.example.oms.repository;

import com.example.oms.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Page<Order> findByCustomerEmail(String customerEmail, Pageable pageable);
    Page<Order> findByOrderDate(LocalDate date, Pageable pageable);
    Page<Order> findByOrderDateBetween(LocalDate start, LocalDate end, Pageable pageable);
}
