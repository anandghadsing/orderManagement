package com.example.oms.controller;

import com.example.oms.dto.BulkUploadReport;
import com.example.oms.entity.Product;
import com.example.oms.service.ProductService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService service;
    public ProductController(ProductService service){ this.service = service; }

    @GetMapping
    public List<Product> all(){ return service.findAll(); }

    @PostMapping(value="/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public BulkUploadReport upload(@RequestParam("file") MultipartFile file) throws Exception {
        return service.bulkUpload(file);
    }
}
