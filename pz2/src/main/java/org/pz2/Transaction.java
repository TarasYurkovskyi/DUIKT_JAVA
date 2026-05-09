package org.pz2;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Transaction {
    private String date;
    private double amount;
    private String description;
}
