package com.StockManagement.DTO;

import com.StockManagement.Domain.Availability;
import com.StockManagement.Domain.Product;

import java.math.BigDecimal;

public record Product_ResponseDTO (Long id, String name, BigDecimal price, Integer stock_quntty
        , Availability availability){
    public  Product_ResponseDTO(Product product){
        this(product.getId(), product.getName(), product.getPrice(), product.getStock_quantity(),
        product.getAvailability());
    }
}
