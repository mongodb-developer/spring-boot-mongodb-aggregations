package com.tutorialCrud.repository;

import com.tutorialCrud.dto.GroupDTO;
import com.tutorialCrud.dto.SalesDTO;
import com.tutorialCrud.dto.TotalSalesDTO;
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
}
