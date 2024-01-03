package com.StockManagement.Controller;

import com.StockManagement.DTO.Product_RequestDTO;
import com.StockManagement.DTO.Product_ResponseDTO;
import com.StockManagement.Domain.Product;
import com.StockManagement.Repository.Product_Repository;
import com.StockManagement.Services.Product_Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("stock/product")
public class Product_Controller {
    @Autowired
    private Product_Repository productRepository;
    @Autowired
    private Product_Services productServices;
    @GetMapping
    public List<Product_ResponseDTO> getProducts(){
        List<Product_ResponseDTO> productList = productRepository.findAll().stream().
                map(Product_ResponseDTO::new).toList();
        return productList;
    }

    @PostMapping
    public Product_RequestDTO insertProduct(@RequestBody  Product_RequestDTO data){
        Product product = new Product(data);
        productServices.setProductAvailabilty(product);
        productRepository.save(product);
        return data;
    }
}
