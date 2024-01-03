package com.StockManagement.Controller;

import com.StockManagement.DTO.Product_RequestDTO;
import com.StockManagement.DTO.Product_ResponseDTO;
import com.StockManagement.Domain.Product;
import com.StockManagement.Repository.Product_Repository;
import com.StockManagement.Services.Product_Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("stock/product")
public class Product_Controller {
    @Autowired
    private Product_Repository productRepository;
    @Autowired
    private Product_Services productServices;

    @PostMapping
    public Product_RequestDTO insertProduct(@RequestBody Product_RequestDTO data) {
        Product product = new Product(data);
        productServices.setProductAvailabilty(product);
        productRepository.save(product);
        return data;
    }

    @GetMapping
    public List<Product_ResponseDTO> getProducts() {
        List<Product_ResponseDTO> productList = productRepository.findAll().stream().
                map(Product_ResponseDTO::new).toList();
        return productList;
    }

    @PutMapping
    public Product_RequestDTO replaceProduct(@RequestBody Product_RequestDTO data) {
        Product product = productServices.findProductByNameORThrowException(data.name());
        product.setPrice(data.price());
        product.setStock_quantity(data.stock_quantity());
        productServices.setProductAvailabilty(product);
        productRepository.save(product);
        return data;
    }

    @DeleteMapping

    public Product_RequestDTO deleteProduct(@RequestBody Product_RequestDTO data) {
        Product product = productServices.findProductByNameORThrowException(data.name());
        productRepository.delete(product);
        return data;
    }

}
