package com.phoebus.model.entites.DTO;

import com.phoebus.model.entites.Product;

import io.micronaut.serde.annotation.Serdeable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Serdeable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    private String name;
    private Double price;

    public static ProductDTO convertProductDTO(Product product) {
        return new ProductDTO(
                product.getId(),
                product.getName(),
                product.getPrice()
        );
    }

}