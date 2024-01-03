package com.StockManagement.Services;

import com.StockManagement.Domain.Availability;
import com.StockManagement.Domain.Product;
import com.StockManagement.Repository.Product_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service

public class Product_Services {
    @Autowired
    private Product_Repository productRepository;
    public Product findProductByNameORThrowException(String name){
        return productRepository.findProductByName(name).
                orElseThrow(()->new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product Not Found"));
    }

    public void setProductAvailabilty(Product product) {
        if (product.getStock_quantity() != 0) {
            product.setAvailability(Availability.in_stock);
        } else {
            product.setAvailability(Availability.out_of_stock);
        }

    }
}
