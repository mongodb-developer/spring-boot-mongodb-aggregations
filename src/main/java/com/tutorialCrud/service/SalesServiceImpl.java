package com.tutorialCrud.service;

import com.tutorialCrud.dto.*;
import com.tutorialCrud.exceptions.EntityNotFoundException;
import com.tutorialCrud.model.Sales;
import com.tutorialCrud.repository.SalesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalesServiceImpl implements SalesService {

    private final SalesRepository salesRepository;

    public SalesServiceImpl(SalesRepository salesRepository) {
        this.salesRepository = salesRepository;
    }

    @Override
    public List<SalesDTO> findAll() {
        List<SalesDTO> salesDTOS = salesRepository.findAll().stream().map(SalesDTO::new).toList();
        if (salesDTOS.isEmpty()) {
            throw new EntityNotFoundException("SalesServiceImpl#findAll");
        }
        return salesDTOS;
    }
    @Override
    public Sales findOne(String id) {
        Sales sale = salesRepository.findOne(id);
        if(sale == null){
            throw new EntityNotFoundException("SalesServiceImpl#findAll");
        }
        return sale;
    }

    @Override
    public Sales updateSale(SalesDTO salesDTO) {
        Sales updatedSale = salesRepository.update(salesDTO.toSales());
        if (updatedSale == null) {
            throw new EntityNotFoundException("SalesServiceImpl#update");
        }
        return new SalesDTO(updatedSale).toSales();
    }

    @Override
    public Long deleteSale(String id) {
        if (id == null || id.isEmpty()) {
            throw new EntityNotFoundException("SalesServiceImpl#delete");
        }
        return salesRepository.delete(id);
    }

    //Aggregation Methods
    @Override
    public List<SalesDTO> matchAggregationOp(String matchValue) {
        return salesRepository.matchOp(matchValue);
    }

    @Override
    public List<GroupDTO> groupAggregation(String matchValue) {
        return salesRepository.groupOp(matchValue);
    }

    @Override
    public List<TotalSalesDTO> findTotalSales() {
        return salesRepository.TotalSales();
    }

    @Override
    public List<PopularDTO> findPopularItems() {
        return salesRepository.findPopularItems();
    }
    @Override
    public List<BucketsDTO> findTotalSpend(){
        return salesRepository.findTotalSpend();
    }
    @Override
    public SalesDTO save(SalesDTO salesDTO) {
        return new SalesDTO(salesRepository.save(salesDTO.toSales()));
    }
}
