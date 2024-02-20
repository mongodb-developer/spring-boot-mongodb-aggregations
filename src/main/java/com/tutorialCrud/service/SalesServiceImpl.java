package com.tutorialCrud.service;

import com.tutorialCrud.Dto.SalesDTO;
import com.tutorialCrud.exceptions.EntityNotFoundException;
import com.tutorialCrud.model.Sales;
import com.tutorialCrud.repository.SalesRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;


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
        return new SalesDTO((salesRepository.update(salesDTO.toSales())));
    }

    @Override
    public Long deleteSale(String id) {
        return salesRepository.delete(id);
    }

    //Aggregation Methods
    @Override
    public List<Sales> matchAggregationOp(String matchValue) {
        // todo move code to repository instead
        MatchOperation matchStage = match(new Criteria("storeLocation").is(matchValue));
        ProjectionOperation projectStage = project("items");
        Aggregation aggregation = newAggregation(matchStage, projectStage);
        return mongoTemplate.aggregate(aggregation, "sales", Sales.class).getMappedResults();
    }

    @Override
    public List<Map> groupAggregation(String matchValue) {
        MatchOperation matchStage = match(new Criteria("storeLocation").is(matchValue));
        GroupOperation groupStage = group("storeLocation").count()
                                                          .as("totalSales")
                                                          .avg("customer.satisfaction")
                                                          .as("averageSatisfaction");
        ProjectionOperation projectStage = project("storeLocation", "totalSales", "averageSatisfaction");
        Aggregation aggregation = newAggregation(matchStage, groupStage, projectStage);
        // todo remove Map.class => DTO instead?
        return mongoTemplate.aggregate(aggregation, "sales", Map.class).getMappedResults();
    }


    @Override
    public List<Map> findTotalSales() {
        GroupOperation groupStage = group("storeLocation").count().as("totalSales");
        SkipOperation skipStage = skip(0);
        LimitOperation limitStage = limit(10);
        Aggregation aggregation = newAggregation(groupStage, skipStage, limitStage);
        // todo remove Map.class => DTO instead?
        return mongoTemplate.aggregate(aggregation, "sales", Map.class).getMappedResults();
    }
}
