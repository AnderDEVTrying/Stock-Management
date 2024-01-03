package com.StockManagement.Repository;

import com.StockManagement.Domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface Product_Repository extends JpaRepository<Product, Long> {
   Optional<Product> findProductByName(String name);
}
