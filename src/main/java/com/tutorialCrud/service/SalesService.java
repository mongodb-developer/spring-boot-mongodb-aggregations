package com.tutorialCrud.service;

import com.tutorialCrud.model.Sales;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public interface SalesService {


    List<Sales> getAllSales();

    Sales getSaleById(String id);

    Sales createSale(Sales sale);

    Sales updateSale(String id, Sales sale);

    void deleteSale(String id);

    BigDecimal getTotalSalesAmount();

    List<Sales> getSalesByTag(String tag);

    List<Sales> getSalesByPurchaseMethod(String purchaseMethod);


    Stream<Sales> getSingleSale();

    Sales getSaleByLocation(String location);

    List<Sales> matchAggregationOp(String matchValue);

    List<Map> groupAggregation(String matchValue);
}
