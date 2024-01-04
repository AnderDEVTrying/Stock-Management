package com.StockManagement.DTO;

import com.StockManagement.Domain.Product;
import com.StockManagement.Domain.Requisition;

import java.math.BigDecimal;

public record Requisition_ResponseDTO(Long id, String name, Product product, Integer requisition_quantity,
BigDecimal totalPrice) {
    public  Requisition_ResponseDTO(Requisition requisition){
        this(requisition.getId(), requisition.getName(), requisition.getProduct(), requisition.getRequisition_quantity()
        ,requisition.getTotal_price());
    }
}
