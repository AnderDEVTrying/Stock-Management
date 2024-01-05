package com.StockManagement.Services;

import com.StockManagement.Domain.Product;
import com.StockManagement.Domain.Requisition;
import com.StockManagement.Repository.Product_Repository;
import com.StockManagement.Repository.Requisition_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * This Class represents the business services, rules and validations associated with the Requisitions operations
 * It provides methods to handle operations such as:
 * Calculate the total price of the product ordered;
 * Reduce the stock quantity based on the quantity of the ordered product;
 */

@Service
public class Requisition_Services {
    @Autowired
    private Requisition_Repository repository;
    @Autowired
    private Product_Repository productRepository;
    @Autowired
    private Product_Services productServices;

    /**
     * This method Calculates the total price of the product ordered by the user
     * The total price is calculated by multiplying the price for the quantity ordered
     *
     * @param requisition The requisition for which to calculate the total price
     */

    public void calculateTotalPrice(Requisition requisition) {
        // Get the product ordered from the requisition
        Product product = requisition.getProduct();
        // Get basic information to calculate the total price
        BigDecimal totalPrice = BigDecimal.ZERO;
        BigDecimal price = product.getPrice();
        Integer quantity = requisition.getRequisition_quantity();
        // Calculate the total price and Set the total price in the requisition
        totalPrice = price.multiply(BigDecimal.valueOf(quantity));
        requisition.setTotal_price(totalPrice);
    }

    /**
     * This method reduces the stock quantity based on the quantity requested in the requisition
     * The redution only happens if the stock quantity is above the requistion quantity
     * @param requisition The requisition for which to get the product to reduce the stock quantity
     */
    public void reduceQuantityBasedOnOrder(Requisition requisition) {
        Product product = requisition.getProduct();
        int requestedQuantity = requisition.getRequisition_quantity();
        int availableQuantity = product.getStock_quantity();

        if (requestedQuantity <= availableQuantity) {
            int newQuantity = availableQuantity - requestedQuantity;
            product.setStock_quantity(newQuantity);
            productServices.setProductAvailabilty(product);
            productRepository.save(product);
        }
    }

}
