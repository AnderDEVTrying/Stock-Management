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

/**
 * This class is responsible to handle the operations of the products related with the CRUD
 */

@RestController
@RequestMapping("stock/product")
public class Product_Controller {
    @Autowired
    private Product_Repository productRepository;
    @Autowired
    private Product_Services productServices;
    @Autowired
    private Requisition_Repository requisitionRepository;

    /**
     * This method inserts or creates the product
     * Using the created service method to set the product availability before saving in the database
     * @param data The Data received from the DTO
     * @return A response entity message that confirms the success of the operation
     */

    @PostMapping
    public ResponseEntity<String> insertProduct(@RequestBody Product_RequestDTO data) {
        Product product = new Product(data);
        productServices.setProductAvailabilty(product);
        productRepository.save(product);
        return new ResponseEntity<>("Product inserted successfully", HttpStatus.CREATED);
    }

    /**
     * This method gets all products saved in the database
     * @return A list of all products inside the database
     */

    @GetMapping
    public List<Product_ResponseDTO> getProducts() {
        return productRepository.findAll().stream().
                map(Product_ResponseDTO::new).toList();
    }

    /**
     * This method updates the products price or quantity based on the name of the product
     * @param data The data received from the DTO
     * @return A response entity message that confirms the success of the operation
     */
    @PutMapping
    public ResponseEntity<String> replaceProduct(@RequestBody Product_RequestDTO data) {
        Product product = productServices.findProductByNameORThrowException(data.name());
        product.setPrice(data.price());
        product.setStock_quantity(data.stock_quantity());
        productServices.setProductAvailabilty(product);
        productRepository.save(product);
        return new ResponseEntity<>("Product Updated Successfully", HttpStatus.OK);
    }

    /**
     * This method deletes the product from the database.
     * Before deleting the product, this method retrieves all requisitions associated with the product and sets their product reference to null.
     * @param data The data containing the name of the product to be deleted
     * @return A response entity message that confirms the success of the operation
     */

    @DeleteMapping
    public ResponseEntity<String> deleteProduct(@RequestBody Product_RequestDTO data) {
        Product product = productServices.findProductByNameORThrowException(data.name());
        List<Requisition> requisitions = requisitionRepository.findByProduct(product);
        for (Requisition requisition : requisitions) {
            requisition.setProduct(null); //
        }
        productRepository.delete(product);
        return new ResponseEntity<>("Product successfully eliminated.", HttpStatus.OK);
    }

}
