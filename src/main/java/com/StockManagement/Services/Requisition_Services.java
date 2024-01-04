package com.StockManagement.Services;

import com.StockManagement.Domain.Product;
import com.StockManagement.Domain.Requisition;
import com.StockManagement.Repository.Product_Repository;
import com.StockManagement.Repository.Requisition_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class Requisition_Services {
    @Autowired
    private Requisition_Repository repository;
    @Autowired
    private Product_Repository productRepository;
    @Autowired
    private Product_Services productServices;

    public void calculateTotalPrice(Requisition requisition) {
        Product product = requisition.getProduct();
        BigDecimal totalPrice = BigDecimal.ZERO;
        BigDecimal price = product.getPrice();
        Integer quantity = requisition.getRequisition_quantity();
        totalPrice = price.multiply(BigDecimal.valueOf(quantity));
        requisition.setTotal_price(totalPrice);
    }

    public void reduceQuantityBasedOnOrder(Requisition requisition) {
        Product product = requisition.getProduct();
        int reduce = requisition.getRequisition_quantity() - product.getStock_quantity();
        product.setStock_quantity(reduce);
        productServices.setProductAvailabilty(product);
        productRepository.save(product);

    }
}
