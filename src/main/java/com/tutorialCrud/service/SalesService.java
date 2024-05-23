package com.tutorialCrud.service;

import com.tutorialCrud.dto.*;
import com.tutorialCrud.model.Sales;

import java.util.List;

public interface SalesService {

    List<SalesDTO> findAll();

    Sales findOne(String id);

    Sales updateSale(SalesDTO salesDTO);

    Long deleteSale(String id);

    List<SalesDTO> matchAggregationOp(String matchValue);

    List<GroupDTO> groupAggregation(String matchValue);

    List<TotalSalesDTO> findTotalSales();
    List<PopularDTO> findPopularItems();
    List<BucketsDTO> findTotalSpend();
    SalesDTO save(SalesDTO salesDTO);
}
