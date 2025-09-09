# OMS Assignment

Spring Boot 3 + MySQL microservice with:
- CRUD for Orders
- Bulk CSV upload for Customers, Products, Orders
- Role-based auth (Admin/Manager/Operator) via Spring Security (HTTP Basic)
- Swagger UI docs
- Minimal static UI for quick manual tests

## Prerequisites
- JDK 17+
- Maven 3.9+
- MySQL 8+
- Create DB: `CREATE DATABASE omsdb;`

## Configure
Edit `src/main/resources/application.properties` with your MySQL username/password.

## Run
```bash
mvn spring-boot:run
```
Swagger: http://localhost:8080/swagger-ui.html  
Static UI: http://localhost:8080/

## Roles & Access
- admin/admin123 → ADMIN
- manager/manager123 → MANAGER
- operator/operator123 → OPERATOR

Key rules:
- Customers/Products bulk upload → ADMIN only
- Orders bulk upload → ADMIN/OPERATOR
- Orders create/update → ADMIN/OPERATOR
- Orders delete → ADMIN
- All can read orders (ADMIN/MANAGER/OPERATOR)

## Endpoints (main)
- `GET /api/orders` — list
- `GET /api/orders/{id}` — get
- `POST /api/orders` — create
- `PUT /api/orders/{id}` — update
- `DELETE /api/orders/{id}` — delete
- `POST /api/orders/upload` — bulk CSV orders
- `GET /api/orders/schedules?customerEmail=...` or `?date=YYYY-MM-DD` or `?startDate=...&endDate=...`
- `POST /api/customers/upload` — bulk CSV customers (ADMIN)
- `POST /api/products/upload` — bulk CSV products (ADMIN)

## CSV Formats
- customers: `name,email,phone,address`
- products: `sku,name,price`
- orders: `customerEmail,productSku,quantity,status,orderDate(yyyy-MM-dd)`

## Notes
- This project uses in-memory users for simplicity. For production use, replace with persistent users and hashed passwords.
- For large CSVs, consider using Spring Batch and batch inserts.
