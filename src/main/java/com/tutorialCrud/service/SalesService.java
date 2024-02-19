package com.tutorialCrud.service;

import com.tutorialCrud.Dto.SalesDTO;
import com.tutorialCrud.model.Sales;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Map;

public interface SalesService {


    List<SalesDTO> findAll();

    SalesDTO findOne(String id);

    SalesDTO updateSale(SalesDTO salesDTO);

    Long deleteSale(String id);

    List<Sales> matchAggregationOp(String matchValue);

    List<Map> groupAggregation(String matchValue);

    List<Map> findTotalSales();



}
