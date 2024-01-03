package com.StockManagement.Services;

import com.StockManagement.Domain.Order;
import com.StockManagement.Domain.Product;
import com.StockManagement.Repository.Order_Repository;
import org.hibernate.boot.model.internal.BinderHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;

@Service
public class Order_Services {
    @Autowired
    private Order_Repository orderRepository;


    public Order findOrderByNameOrThrowException(String name){
        return orderRepository.findOrderdProductByName(name).
                orElseThrow(()->new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product Not Found for Order"));
    }

    public void calculateTotalPrice(String name, Integer order_quantity, BigDecimal price){
       Order order = findOrderByNameOrThrowException(name);
       BigDecimal totalPrice = price.multiply(BigDecimal.valueOf(order_quantity));
        order.setTotal_price(totalPrice);
    }
}
