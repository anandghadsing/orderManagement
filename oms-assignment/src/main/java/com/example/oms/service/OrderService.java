package com.example.oms.service;

import com.example.oms.entity.Order;
import com.example.oms.exception.NotFoundException;
import com.example.oms.repository.CustomerRepository;
import com.example.oms.repository.OrderRepository;
import com.example.oms.repository.ProductRepository;
import com.example.oms.util.CsvUtils;
import com.example.oms.dto.BulkUploadReport;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepository repo;
    private final CustomerRepository customers;
    private final ProductRepository products;

    public OrderService(OrderRepository repo, CustomerRepository customers, ProductRepository products) {
        this.repo = repo;
        this.customers = customers;
        this.products = products;
    }

    public Order create(Order o){
        customers.findByEmail(o.getCustomerEmail())
                .orElseThrow(() -> new NotFoundException("Customer not found: " + o.getCustomerEmail()));
        products.findBySku(o.getProductSku())
                .orElseThrow(() -> new NotFoundException("Product not found: " + o.getProductSku()));
        return repo.save(o);
    }

    public Order update(Long id, Order o){
        Order existing = repo.findById(id)
                .orElseThrow(() -> new NotFoundException("Order not found: " + id));
        existing.setCustomerEmail(o.getCustomerEmail());
        existing.setProductSku(o.getProductSku());
        existing.setQuantity(o.getQuantity());
        existing.setStatus(o.getStatus());
        existing.setOrderDate(o.getOrderDate());
        return create(existing);
    }

    public void delete(Long id){ repo.deleteById(id); }

    public Order get(Long id){
        return repo.findById(id).orElseThrow(() -> new NotFoundException("Order not found: " + id));
    }

    public List<Order> list(){ return repo.findAll(); }

    public List<Order> schedulesByCustomer(String email, int page, int size){
        return repo.findByCustomerEmail(email, PageRequest.of(page, size)).getContent();
    }

    public List<Order> schedulesByDate(LocalDate date, int page, int size){
        return repo.findByOrderDate(date, PageRequest.of(page, size)).getContent();
    }

    public List<Order> schedulesBetween(LocalDate start, LocalDate end, int page, int size){
        return repo.findByOrderDateBetween(start, end, PageRequest.of(page, size)).getContent();
    }

    // ✅ Bulk upload matching your sheet (id,customerId,productId,quantity)
    public BulkUploadReport bulkUpload(MultipartFile file) throws Exception {
        List<String[]> rows = CsvUtils.read(file);
        BulkUploadReport report = new BulkUploadReport();
        report.setTotalRows(rows.size());

        for (int i = 0; i < rows.size(); i++) {
            String[] r = rows.get(i);
            try {
                // r[0] = id (ignore, DB auto-generates)
                String customerId = CsvUtils.required(r, 1, "customerId");
                String productId = CsvUtils.required(r, 2, "productId");
                String quantity = CsvUtils.required(r, 3, "quantity");

                Order o = new Order();
                o.setCustomerEmail(
                        customers.findById(Long.parseLong(customerId))
                                .orElseThrow(() -> new NotFoundException("Customer not found: " + customerId))
                                .getEmail()
                );
                o.setProductSku(
                        products.findById(Long.parseLong(productId))
                                .orElseThrow(() -> new NotFoundException("Product not found: " + productId))
                                .getSku()
                );
                o.setQuantity(Integer.parseInt(quantity));
                o.setStatus("NEW"); // default status
                o.setOrderDate(LocalDate.now()); // default date

                create(o);
                report.incSuccess();
            } catch (Exception e) {
                report.incFailure();
                report.addError("Row " + (i + 1) + ": " + e.getMessage());
            }
        }
        return report;
    }
}
