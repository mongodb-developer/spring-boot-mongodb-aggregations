package com.tutorialCrud.Dto;

public record TotalSalesDTO(String id, int totalSales) {
    public TotalSalesDTO(String id, int totalSales) {
        this.id = id;
        this.totalSales = totalSales;
    }
}
