package com.StockManagement.Domain;

import com.StockManagement.DTO.Order_RequestDTO;
import com.StockManagement.Repository.Product_Repository;
import com.StockManagement.Services.Product_Services;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity(name = "orders")
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String user;
    @OneToMany
    @JoinColumn(name = "Product_id")
    private List<Product> products;
    private Integer order_quantity;
    private BigDecimal total_price;

    public Order(Order_RequestDTO data, Product_Services productServices) {
        this.user = data.name();
        Product product = productServices.findProductByNameORThrowException(data.product_name());
        this.products = new ArrayList<>();
        this.products.add(product);
        this.order_quantity = data.order_quantity();
    }
}
