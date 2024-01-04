package com.StockManagement.Repository;

import com.StockManagement.Domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface Order_Repository extends JpaRepository<Order, Long> {
    Optional<Order> findOrderdProductByName(String name);
}
