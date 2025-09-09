package com.example.oms.controller;

import com.example.oms.dto.BulkUploadReport;
import com.example.oms.entity.Order;
import com.example.oms.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService service;
    public OrderController(OrderService service){ this.service = service; }

    @PostMapping
    public Order create(@RequestBody @Valid Order order){ return service.create(order); }

    @GetMapping
    public List<Order> all(){ return service.list(); }

    @GetMapping("/{id}")
    public Order get(@PathVariable Long id){ return service.get(id); }

    @PutMapping("/{id}")
    public Order update(@PathVariable Long id, @RequestBody @Valid Order order){ return service.update(id, order); }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){ service.delete(id); }

    @PostMapping(value="/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public BulkUploadReport upload(@RequestParam("file") MultipartFile file) throws Exception {
        return service.bulkUpload(file);
    }

    @GetMapping("/schedules")
    public List<Order> schedules(
            @RequestParam(required = false) String customerEmail,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size
    ){
        if (customerEmail != null) return service.schedulesByCustomer(customerEmail, page, size);
        if (date != null) return service.schedulesByDate(date, page, size);
        if (startDate != null && endDate != null) return service.schedulesBetween(startDate, endDate, page, size);
        return service.list();
    }
}
