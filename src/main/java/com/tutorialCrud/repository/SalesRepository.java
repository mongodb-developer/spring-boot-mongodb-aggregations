package com.tutorialCrud.repository;

import com.tutorialCrud.Dto.SalesDTO;
import com.tutorialCrud.Dto.TotalSalesDTO;
import com.tutorialCrud.model.Sales;

import java.util.List;
import java.util.Map;

public interface SalesRepository{

   List<Sales> findAll();

    Sales update(Sales sale);

    Sales findOne(String id);

    Long delete(String id);
    List<SalesDTO> matchOp(String matchValue);
    List<Map> groupOp(String matchValue);
    List<TotalSalesDTO> TotalSales();
}
