package com.StockManagement.Repository;

import com.StockManagement.Domain.Product;
import com.StockManagement.Domain.Requisition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Requisition_Repository extends JpaRepository<Requisition, Long> {
    List<Requisition> findByProduct(Product product);

}
