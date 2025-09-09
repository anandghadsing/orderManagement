package com.example.oms.controller;

import com.example.oms.dto.BulkUploadReport;
import com.example.oms.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadCustomers(@RequestParam("file") MultipartFile file) {
        try {
            BulkUploadReport report = customerService.bulkUpload(file);
            return ResponseEntity.ok(report);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Upload failed: " + e.getMessage());
        }
    }
}
