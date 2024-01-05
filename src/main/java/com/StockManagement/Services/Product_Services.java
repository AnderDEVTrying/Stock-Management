package com.StockManagement.Services;

import com.StockManagement.Domain.Availability;
import com.StockManagement.Domain.Product;
import com.StockManagement.Repository.Product_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 * This Class represents the business services, rules and validations associated with the products operations
 * It provides methods to handle some operations such as finding the product by its name and set the product availability
 */
@Service

public class Product_Services {
    @Autowired
    private Product_Repository productRepository;

    /**
     * This method finds the product based on its name
     *
     * @param name The name of the product to find
     * @return the product with the specified name
     * @throws ResponseStatusException if the product is not found
     */
    public Product findProductByNameORThrowException(String name) {
        return productRepository.findProductByName(name).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product Not Found"));
    }

    /**
     * Sets the availability of the product based on the stock quantity.
     * If the stock is above 0 the product will be considered "in stock";
     * Else it will be considered "out of stock";
     * @param product the product which the availability will be set
     */
    public void setProductAvailabilty(Product product) {
        if (product.getStock_quantity() > 0) {
            product.setAvailability(Availability.in_stock);
        } else {
            product.setAvailability(Availability.out_of_stock);
        }

    }
}

