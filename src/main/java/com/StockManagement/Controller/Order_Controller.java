package com.StockManagement.Controller;

import com.StockManagement.DTO.Order_RequestDTO;
import com.StockManagement.Domain.Order;
import com.StockManagement.Domain.Product;
import com.StockManagement.Repository.Order_Repository;
import com.StockManagement.Services.Order_Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("stock/order")
@RestController
public class Order_Controller {
    @Autowired
    private Order_Repository orderRepository;
    @Autowired
    private Order_Services orderServices;

    @PostMapping
    public Order_RequestDTO saveOrder(@RequestBody Order_RequestDTO data){
        Order order = new Order();
        order.setUser(data.name());
        return data;
    }
}
