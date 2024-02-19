package com.tutorialCrud.repository;

import com.tutorialCrud.model.Sales;

import java.util.List;
public interface SalesRepository{

   List<Sales> findAll();

    Sales update(Sales sale);

    Sales findOne(String id);

    Long delete(String id);
}
