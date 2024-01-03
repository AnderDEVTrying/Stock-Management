package com.StockManagement.Domain;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.lang.ref.Reference;
import java.math.BigDecimal;
@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;
    private BigDecimal price;
    private Integer stock_quantity;
    @Enumerated(EnumType.STRING)
    private Availability availability;
}
