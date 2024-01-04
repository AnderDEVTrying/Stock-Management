package com.StockManagement.DTO;

import java.math.BigDecimal;

public record Order_RequestDTO(String name, String product_name, Integer order_quantity) {
}
