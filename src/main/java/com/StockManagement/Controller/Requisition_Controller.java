package com.StockManagement.Controller;

import com.StockManagement.DTO.Product_ResponseDTO;
import com.StockManagement.DTO.Requisition_RequestDTO;
import com.StockManagement.DTO.Requisition_ResponseDTO;
import com.StockManagement.Domain.Requisition;
import com.StockManagement.Repository.Requisition_Repository;
import com.StockManagement.Services.Product_Services;
import com.StockManagement.Services.Requisition_Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("stock/order")
public class Requisition_Controller {
    @Autowired
    private Requisition_Services services;
    @Autowired
    private Product_Services productServices;
    @Autowired
    private Requisition_Repository repository;
    @PostMapping

    public ResponseEntity<String> placeOrder(@RequestBody Requisition_RequestDTO data){
        Requisition requisition = new Requisition(data,productServices);
        services.calculateTotalPrice(requisition);
        repository.save(requisition);
        services.reduceQuantityBasedOnOrder(requisition);
        return new ResponseEntity<>("Requisition placed sucessfully", HttpStatus.CREATED);
    }


    @GetMapping
    public List<Requisition_ResponseDTO> getOrders(){
        return repository.findAll().stream().
                map(Requisition_ResponseDTO::new).toList();
    }
}
