package com.tutorialCrud.service;

import com.tutorialCrud.model.Sales;
import com.tutorialCrud.repository.SalesRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@Service
public class SalesServiceImpl implements SalesService {

    private final SalesRepository salesRepository;
    private final MongoTemplate mongoTemplate;

    public SalesServiceImpl(SalesRepository salesRepository, MongoTemplate mongoTemplate) {
        this.salesRepository = salesRepository;
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<Sales> getAllSales() {
        return salesRepository.findAll();
    }

    @Override
    public Sales getSaleById(String id) {
        return salesRepository.findById(id).orElse(null);
    }

    @Override
    public Sales createSale(Sales sale) {
        return salesRepository.save(sale);
    }

    @Override
    public Sales updateSale(String id, Sales sale) {
        if (salesRepository.existsById(id)) {
            sale.setId(id);
            return salesRepository.save(sale);
        }
        return null;
    }

    @Override
    public void deleteSale(String id) {
        salesRepository.deleteById(id);
    }

    @Override
    public BigDecimal getTotalSalesAmount() {
        List<Sales> allSales = salesRepository.findAll();
        return allSales.stream()
                       .map(sale -> sale.getItems()
                                        .stream()
                                        .map(item -> item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                                        .reduce(BigDecimal.ZERO, BigDecimal::add))
                       .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public List<Sales> getSalesByTag(String tag) {
        return salesRepository.findByItemsTagsContaining(tag);
    }

    @Override
    public List<Sales> getSalesByPurchaseMethod(String purchaseMethod) {
        return salesRepository.findByPurchaseMethod(purchaseMethod);
    }

    @Override
    public Stream<Sales> getSingleSale() {
        Stream<Sales> optionalSales = salesRepository.findAll().stream().limit(1);
        return optionalSales;
    }


    @Override
    public Sales getSaleByLocation(String location) {
        Query query = new Query();
        query.addCriteria(Criteria.where("storeLocation").is(location));
        query.limit(1);
        return mongoTemplate.findOne(query, Sales.class);
    }

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
                                               .count()
                                               .as("totalSales")
                                               .avg("customer.satisfaction")
                                               .as("averageSatisfaction");

        ProjectionOperation projectStage = Aggregation.project("storeLocation", "totalSales", "averageSatisfaction");

        Aggregation aggregation = Aggregation.newAggregation(matchStage, groupStage, projectStage);

        return mongoTemplate.aggregate(aggregation, "sales", Map.class).getMappedResults();
    }

}
