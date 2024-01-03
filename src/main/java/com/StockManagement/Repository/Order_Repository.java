package com.StockManagement.Repository;

import com.StockManagement.Domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Order_Repository extends JpaRepository<Order, Long> {
}
