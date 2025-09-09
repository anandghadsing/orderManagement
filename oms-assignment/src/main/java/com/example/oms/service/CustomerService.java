package com.example.oms.service;

import com.example.oms.dto.BulkUploadReport;
import com.example.oms.entity.Customer;
import com.example.oms.repository.CustomerRepository;
import com.example.oms.util.CsvUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.regex.Pattern;

@Service
public class CustomerService {

    private final CustomerRepository repo;

    private static final Pattern EMAIL_REGEX = Pattern.compile("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$");
    private static final Pattern PHONE_REGEX = Pattern.compile("^\\d{10}$");

    public CustomerService(CustomerRepository repo) {
        this.repo = repo;
    }

    public List<Customer> findAll() {
        return repo.findAll();
    }

    public BulkUploadReport bulkUpload(MultipartFile file) throws Exception {
        List<String[]> rows = CsvUtils.read(file);
        BulkUploadReport report = new BulkUploadReport();
        report.setTotalRows(rows.size());

        for (int i = 0; i < rows.size(); i++) {
            String[] r = rows.get(i);
            try {
            	String id = CsvUtils.required(r, 0, "id");
                String name = CsvUtils.required(r, 1, "name");
                String email = CsvUtils.required(r, 2, "email");
                String phone = CsvUtils.required(r, 3, "phone");
                String address = CsvUtils.required(r, 4, "address");

                if (!EMAIL_REGEX.matcher(email).matches()) {
                    throw new IllegalArgumentException("Invalid email → " + email);
                }

                if (!PHONE_REGEX.matcher(phone).matches()) {
                    throw new IllegalArgumentException("Invalid phone → " + phone);
                }

                // Create new customer object
                Customer c = new Customer();
                c.setName(name);
                c.setEmail(email);
                c.setPhone(phone);
                c.setAddress(phone);

                repo.findByEmail(email).ifPresentOrElse(existing -> {
                    existing.setName(name);
                    existing.setPhone(phone);
                    existing.setAddress(address); 
                    repo.save(existing);
                },
                		() -> repo.save(c));

                report.incSuccess();

            } catch (Exception e) {
                report.incFailure();
                report.addError("Row " + (i + 1) + ": " + e.getMessage());
            }
        }
        return report;
    }
}
