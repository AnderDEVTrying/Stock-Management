package com.StockManagement.Domain;

import com.StockManagement.DTO.Product_RequestDTO;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    /**
     * Represents a product in the stock management
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;
    private BigDecimal price;
    private Integer stock_quantity;
    @Enumerated(EnumType.STRING)
    private Availability availability;

    /**
     * Creates a product based on the DTO information
     * @param requestDTO DTO with the necessary information to create a Product
     */

    public Product(Product_RequestDTO requestDTO){
        this.name = requestDTO.name();
        this.price = requestDTO.price();
        this.stock_quantity = requestDTO.stock_quantity();
    }
}
