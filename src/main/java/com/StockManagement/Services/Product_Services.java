package com.StockManagement.Services;

import com.StockManagement.Repository.Product_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class Product_Services {
    @Autowired
    private Product_Repository productRepository;
}
