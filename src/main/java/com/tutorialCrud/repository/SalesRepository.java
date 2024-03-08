package com.tutorialCrud.repository;

import com.tutorialCrud.dto.*;
import com.tutorialCrud.model.Sales;

import java.util.List;

public interface SalesRepository {

    List<Sales> findAll();
    Sales findOne(String id);
    Sales update(Sales sale);
    Long delete(String id);
    List<SalesDTO> matchOp(String matchValue);
    List<GroupDTO> groupOp(String matchValue);
    List<TotalSalesDTO> TotalSales();
    List<PopularDTO> findPopularItems();
    List<BucketsDTO> findTotalSpend();
}
