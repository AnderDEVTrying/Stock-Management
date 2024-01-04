package com.StockManagement.Domain;

import com.StockManagement.DTO.Requisition_RequestDTO;
import com.StockManagement.Services.Product_Services;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity(name = "requisitions")
@Table(name = "requisitions")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
public class Requisition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToOne(fetch = FetchType.EAGER)
    private Product product;
    private Integer requisition_quantity;
    private BigDecimal total_price;

    public Requisition(Requisition_RequestDTO data, Product_Services services) {
        this.name = data.name();
        this.product = services.findProductByNameORThrowException(data.product_name());
        this.requisition_quantity = data.requisition_quantity();
    }

}
