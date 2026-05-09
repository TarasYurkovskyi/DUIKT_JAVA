package org.pz4.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Clothing extends Product {
    private String size;
    private String color;
    private String material;


    @Override
    public String getProductType() {
        return "Одяг";
    }
}

