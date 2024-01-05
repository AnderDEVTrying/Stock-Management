package com.StockManagement.Controller;

import com.StockManagement.DTO.Product_RequestDTO;
import com.StockManagement.DTO.Product_ResponseDTO;
import com.StockManagement.Domain.Product;
import com.StockManagement.Domain.Requisition;
import com.StockManagement.Repository.Product_Repository;
import com.StockManagement.Repository.Requisition_Repository;
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
    @Autowired
    private Requisition_Repository requisitionRepository;

    @PostMapping
    public ResponseEntity<String> insertProduct(@RequestBody Product_RequestDTO data) {
        Product product = new Product(data);
        productServices.setProductAvailabilty(product);
        productRepository.save(product);
        return new ResponseEntity<>("Product inserted sucessfully", HttpStatus.CREATED);
    }

    @GetMapping
    public List<Product_ResponseDTO> getProducts() {
        return productRepository.findAll().stream().
                map(Product_ResponseDTO::new).toList();
    }

    @PutMapping
    public ResponseEntity<String> replaceProduct(@RequestBody Product_RequestDTO data) {
        Product product = productServices.findProductByNameORThrowException(data.name());
        product.setPrice(data.price());
        product.setStock_quantity(data.stock_quantity());
        productServices.setProductAvailabilty(product);
        productRepository.save(product);
        return new ResponseEntity<>("Product Updated Sucessefully", HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteProduct(@RequestBody Product_RequestDTO data) {
        Product product = productServices.findProductByNameORThrowException(data.name());
        List<Requisition> requisitions = requisitionRepository.findByProduct(product);
        for (Requisition requisition : requisitions) {
            requisition.setProduct(null); //
        }
        productRepository.delete(product);
        return new ResponseEntity<>("Product sucessefully eliminated.", HttpStatus.OK);
    }

}
