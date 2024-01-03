package com.StockManagement.Services;

import com.StockManagement.Domain.Availability;
import com.StockManagement.Domain.Product;
import com.StockManagement.Repository.Product_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class Product_Services {
    @Autowired
    private Product_Repository productRepository;
    public void setProductAvailabilty(Product product) {
        if(product.getStock_quantity()!= 0){
            product.setAvailability(Availability.in_stock);
        }else {
        product.setAvailability(Availability.out_of_stock);}

    }
}
