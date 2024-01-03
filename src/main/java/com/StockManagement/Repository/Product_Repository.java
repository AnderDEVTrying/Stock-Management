package com.StockManagement.Repository;

import com.StockManagement.Domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Product_Repository extends JpaRepository<Product, Long> {
}
