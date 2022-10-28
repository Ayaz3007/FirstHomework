package com.example.databasesservlets.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Relation {
    private Long id;
    private Long productId;
    private Long storeId;
    private int amount;

    public Relation(Long productId, Long storeId, int amount) {
        this.productId = productId;
        this.storeId = storeId;
        this.amount = amount;
    }
}
