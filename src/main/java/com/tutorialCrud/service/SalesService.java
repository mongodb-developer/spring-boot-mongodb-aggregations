package com.tutorialCrud.service;


import com.tutorialCrud.Dto.SalesDTO;
import com.tutorialCrud.Dto.TotalSalesDTO;

import java.util.List;
import java.util.Map;

public interface SalesService {

    List<SalesDTO> findAll();

    SalesDTO findOne(String id);

    SalesDTO updateSale(SalesDTO salesDTO);

    Long deleteSale(String id);

    List<SalesDTO> matchAggregationOp(String matchValue);

    List<Map> groupAggregation(String matchValue);

    List<TotalSalesDTO> findTotalSales();
}
