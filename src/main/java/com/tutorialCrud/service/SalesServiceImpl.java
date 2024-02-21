package com.tutorialCrud.service;


import com.tutorialCrud.Dto.SalesDTO;
import com.tutorialCrud.Dto.TotalSalesDTO;
import com.tutorialCrud.exceptions.EntityNotFoundException;
import com.tutorialCrud.model.Sales;
import com.tutorialCrud.repository.SalesRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class SalesServiceImpl implements SalesService {

    private final SalesRepository salesRepository;
    private final MongoTemplate mongoTemplate;

    public SalesServiceImpl(SalesRepository salesRepository, MongoTemplate mongoTemplate) {
        this.salesRepository = salesRepository;
        this.mongoTemplate = mongoTemplate; // todo remove?
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
    public SalesDTO findOne(String id) {
        Sales sale = salesRepository.findOne(id);
        if (sale == null) {
            throw new EntityNotFoundException("SalesServiceImpl#findOne");
        }
        return new SalesDTO(sale);
    }

    @Override
    public SalesDTO updateSale(SalesDTO salesDTO) {
        Sales updatedSale = salesRepository.update(salesDTO.toSales());
        if (updatedSale == null)
            throw new EntityNotFoundException("SalesServiceImpl#update");
        return new SalesDTO(updatedSale);
    }

    @Override
    public Long deleteSale(String id) {
        if (id == null)
            throw new EntityNotFoundException("SalesServiceImpl#delete");
        return salesRepository.delete(id);
    }

    //Aggregation Methods
    @Override
    public List<SalesDTO> matchAggregationOp(String matchValue) {
        return salesRepository.matchOp(matchValue);
    }

    @Override
    public List<Map> groupAggregation(String matchValue) {
       return salesRepository.groupOp(matchValue);
    }


    @Override
    public List<TotalSalesDTO> findTotalSales() {
        return salesRepository.TotalSales();
    }
}
