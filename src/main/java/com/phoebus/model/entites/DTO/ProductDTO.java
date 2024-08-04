package com.phoebus.model.entites.DTO;

import com.phoebus.model.entites.Product;

import io.micronaut.core.annotation.Creator;
import io.micronaut.serde.annotation.Serdeable;
import lombok.Data;

import java.io.Serializable;

@Serdeable
@Data
public class ProductDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    private String name;
    private Double price;


    @Creator
    public ProductDTO(Long id, String name, Double price) {
        this.id = id;
        this.name = name;
        this.price = price;

    }

    public static ProductDTO convertProductDTO(Product product) {
        return new ProductDTO(
                product.getId(),
                product.getName(),
                product.getPrice()
        );
    }

}