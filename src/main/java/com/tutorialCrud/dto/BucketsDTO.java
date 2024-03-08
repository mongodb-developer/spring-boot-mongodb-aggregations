package com.tutorialCrud.dto;

import org.bson.types.Decimal128;

import java.math.BigDecimal;

public record BucketsDTO(String id, int count, BigDecimal totalAmount) {
    public BucketsDTO(String id, int count, BigDecimal totalAmount){
        this.id = id;
        this.count = count;
        this.totalAmount = totalAmount;
    }
}
