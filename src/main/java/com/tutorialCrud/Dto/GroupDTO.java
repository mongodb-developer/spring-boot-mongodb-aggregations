package com.tutorialCrud.Dto;

public record GroupDTO(String _id, int totalSales, double averageSatisfaction) {
    public GroupDTO(String _id, int totalSales, double averageSatisfaction) {
        this._id = _id;
        this.totalSales = totalSales;
        this.averageSatisfaction = averageSatisfaction;
    }
}