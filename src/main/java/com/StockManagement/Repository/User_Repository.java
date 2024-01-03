package com.StockManagement.Repository;

import com.StockManagement.Domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  User_Repository extends JpaRepository<User, Long> {
}
