package com.tutorialCrud.service;

import com.tutorialCrud.Dto.SalesDTO;
import com.tutorialCrud.model.Sales;
import com.tutorialCrud.repository.SalesRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class SalesServiceImpl implements SalesService {

    private final SalesRepository salesRepository;
    private final MongoTemplate mongoTemplate;

    public SalesServiceImpl(SalesRepository salesRepository, MongoTemplate mongoTemplate) {
        this.salesRepository = salesRepository;
        this.mongoTemplate = mongoTemplate;
    }


    @Override
    public List<SalesDTO> findAll() {
        return salesRepository.findAll().stream().map(SalesDTO::new).toList();
    }

    @Override
    public SalesDTO findOne(String id) {
        return new SalesDTO(salesRepository.findOne(id));
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
        MatchOperation matchStage = Aggregation.match(new Criteria("storeLocation").is(matchValue));
        ProjectionOperation projectStage = Aggregation.project("items");
        Aggregation aggregation = Aggregation.newAggregation(matchStage, projectStage);
        return mongoTemplate.aggregate(aggregation, "sales", Sales.class).getMappedResults();
    }
    @Override
    public List<Map> groupAggregation(String matchValue) {

        MatchOperation matchStage = Aggregation.match(new Criteria("storeLocation").is(matchValue));

        GroupOperation groupStage = Aggregation.group("storeLocation")
                .count().as("totalSales")
                .avg("customer.satisfaction").as("averageSatisfaction");

        ProjectionOperation projectStage = Aggregation.project("storeLocation", "totalSales", "averageSatisfaction");

        Aggregation aggregation = Aggregation.newAggregation(matchStage, groupStage, projectStage);

        return mongoTemplate.aggregate(aggregation, "sales", Map.class).getMappedResults();
    }


    @Override
    public List<Map> findTotalSales(){

        GroupOperation groupStage = Aggregation.group("storeLocation")
                .count().as("totalSales");
        SkipOperation skipStage = Aggregation.skip(0);
        LimitOperation limitStage = Aggregation.limit(10);

        Aggregation aggregation = Aggregation.newAggregation(groupStage, skipStage, limitStage);

        return mongoTemplate.aggregate(aggregation, "sales", Map.class).getMappedResults();

    }
}
