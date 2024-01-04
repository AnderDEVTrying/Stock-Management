package com.StockManagement.Repository;

import com.StockManagement.Domain.Requisition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Requisition_Repository extends JpaRepository<Requisition, Long> {
}
