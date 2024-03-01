package com.tutorialCrud.service;

<<<<<<< HEAD

import com.tutorialCrud.dto.GroupDTO;
import com.tutorialCrud.dto.SalesDTO;
import com.tutorialCrud.dto.TotalSalesDTO;
import com.tutorialCrud.model.Sales;
=======
import com.tutorialCrud.dto.GroupDTO;
import com.tutorialCrud.dto.SalesDTO;
import com.tutorialCrud.dto.TotalSalesDTO;
>>>>>>> origin/maxime

import java.util.List;

public interface SalesService {

    List<SalesDTO> findAll();

    Sales findOne(String id);

    Sales updateSale(SalesDTO salesDTO);

    Long deleteSale(String id);

    List<SalesDTO> matchAggregationOp(String matchValue);

    List<GroupDTO> groupAggregation(String matchValue);

    List<TotalSalesDTO> findTotalSales();
}
