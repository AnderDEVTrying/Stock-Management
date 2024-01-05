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
/**
 * This class is responsible to handle the operations of the Requisitions related with the CRUD
 */

@RestController
@RequestMapping("stock/order")
public class Requisition_Controller {
    @Autowired
    private Requisition_Services services;
    @Autowired
    private Product_Services productServices;
    @Autowired
    private Requisition_Repository repository;

    /**
     * This method creates a requisition or order.
     * It uses methods from the requisition Services to calculate the total price of the product ordered
     * Then saves the order and after that updates the stock quantity
     * @param data The data received from the DTO to be inserted in the database
     * @return A response entity message that confirms the success of the operation
     */
    @PostMapping

    public ResponseEntity<String> placeOrder(@RequestBody Requisition_RequestDTO data){
        Requisition requisition = new Requisition(data,productServices);
        services.calculateTotalPrice(requisition);
        repository.save(requisition);
        services.reduceQuantityBasedOnOrder(requisition);
        return new ResponseEntity<>("Requisition placed successfully", HttpStatus.CREATED);
    }

    /**
     * This method gets all requisitions placed in the database.
     * @return A list of requisitions placed in the database
     */

    @GetMapping
    public List<Requisition_ResponseDTO> getOrders(){
        return repository.findAll().stream().
                map(Requisition_ResponseDTO::new).toList();
    }
}
