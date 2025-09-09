package com.example.oms.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleCsvController {

    @GetMapping("/api/sample/customers")
    public ResponseEntity<String> sampleCustomers() {
        String csv = "id,name,email,phone,address\n" +
                     "1,Anand,johndoe@example.com,9999999999,Mantha chowk, Jalna\n" +
                     "2,Rahul,doe@example.com,7777777777,nagaram, Hyderabad\n";
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=customers_sample.csv")
                .contentType(MediaType.parseMediaType("text/csv"))
                .body(csv);
    }

    @GetMapping("/api/sample/products")
    public ResponseEntity<String> sampleProducts() {
        String csv = "id,name,price\n" +
                     "1,Laptop,55000\n" +
                     "2,Mouse,500\n";
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=products_sample.csv")
                .contentType(MediaType.parseMediaType("text/csv"))
                .body(csv);
    }

    @GetMapping("/api/sample/orders")
    public ResponseEntity<String> sampleOrders() {
        String csv = "id,customerId,productId,quantity\n" +
                     "1,1,1,1\n" +
                     "2,2,2,3\n";
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=orders_sample.csv")
                .contentType(MediaType.parseMediaType("text/csv"))
                .body(csv);
    }
}
