package org.pz4.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public abstract class Product {
    private int id;
    private String name;
    private double price;
    private String description;

    public abstract String getProductType();
}

