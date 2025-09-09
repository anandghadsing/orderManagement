package com.example.oms.service;

import com.example.oms.dto.BulkUploadReport;
import com.example.oms.entity.Product;
import com.example.oms.repository.ProductRepository;
import com.example.oms.util.CsvUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository repo;

    public ProductService(ProductRepository repo){ 
        this.repo = repo; 
    }

    public List<Product> findAll(){ 
        return repo.findAll(); 
    }

    public BulkUploadReport bulkUpload(MultipartFile file) throws Exception {
        // ✅ CsvUtils.read(file) returns List<String[]>
        List<String[]> rows = CsvUtils.read(file);  
        BulkUploadReport report = new BulkUploadReport();
        report.setTotalRows(rows.size());

        for (int i = 0; i < rows.size(); i++) {
            String[] r = rows.get(i);   // ✅ each row is already String[]

            try {
                Product p = new Product();
                p.setSku(CsvUtils.required(r, 0, "sku"));
                p.setName(CsvUtils.required(r, 1, "name"));
                p.setPrice(Double.valueOf(CsvUtils.required(r, 2, "price")));

                repo.findBySku(p.getSku()).ifPresentOrElse(
                    existing -> { 
                        existing.setName(p.getName()); 
                        existing.setPrice(p.getPrice()); 
                        repo.save(existing); 
                    },
                    () -> repo.save(p)
                );

                report.incSuccess();
            } catch (Exception e) {
                report.incFailure();
                report.addError("Row " + (i + 1) + ": " + e.getMessage());
            }
        }
        return report;
    }
}
