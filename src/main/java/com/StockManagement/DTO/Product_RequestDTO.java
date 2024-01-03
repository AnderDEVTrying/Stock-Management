package com.StockManagement.DTO;

import java.math.BigDecimal;

public record Product_RequestDTO(String name, BigDecimal price, Integer stock_qntty) {
}
