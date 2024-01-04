package com.StockManagement.Domain;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Entity(name = "requisitions")
@Table(name = "requisitions")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Requisition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany
    @JoinColumn(name = "Product_id")
    private List<Product> product;
    private Integer order_quantity;
    private BigDecimal total_price;

}
