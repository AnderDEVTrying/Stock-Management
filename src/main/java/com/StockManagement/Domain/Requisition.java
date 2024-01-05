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
    /**
     * Represents the orders place by the user
     * Gets data of the ordered product from the Product Database
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToOne(fetch = FetchType.EAGER)
    private Product product;
    private Integer requisition_quantity;
    private BigDecimal total_price;

    /**
     * Creates a Requisition(or Order) based on the DTO information
     * @param data DTO with the necessary information to create a Requisition
     * @param services Necessary services to search for the product based on the name
     */

    public Requisition(Requisition_RequestDTO data, Product_Services services) {
        this.name = data.name();
        this.product = services.findProductByNameORThrowException(data.product_name());
        this.requisition_quantity = data.requisition_quantity();
    }

}
