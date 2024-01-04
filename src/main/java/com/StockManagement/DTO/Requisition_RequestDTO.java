package com.StockManagement.DTO;

import com.StockManagement.Domain.Product;

import java.util.List;

public record Requisition_RequestDTO(String name, String product_name, Integer requisition_quantity) {
}
