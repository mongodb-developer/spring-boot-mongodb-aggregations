package com.tutorialCrud.service;

import com.tutorialCrud.dto.GroupDTO;
import com.tutorialCrud.dto.SalesDTO;
import com.tutorialCrud.dto.TotalSalesDTO;

import java.util.List;

public interface SalesService {

    List<SalesDTO> findAll();

    SalesDTO findOne(String id);

    SalesDTO updateSale(SalesDTO salesDTO);

    Long deleteSale(String id);

    List<SalesDTO> matchAggregationOp(String matchValue);

    List<GroupDTO> groupAggregation(String matchValue);

    List<TotalSalesDTO> findTotalSales();
}
