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
public class Electronics extends Product {
    private String brand;
    private String warranty;


    @Override
    public String getProductType() {
        return "Електроніка";
    }
}

