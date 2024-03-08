package com.tutorialCrud.repository;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.FindOneAndReplaceOptions;
import com.tutorialCrud.dto.*;
import com.tutorialCrud.model.Sales;
import jakarta.annotation.PostConstruct;
import org.bson.types.ObjectId;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.ReturnDocument.AFTER;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

@Repository
public class MongoDBSaleRepository implements SalesRepository {

    private final MongoTemplate mongoTemplate;
    private final MongoClient mongoclient;
    private MongoCollection<Sales> saleCollection;

    public MongoDBSaleRepository(MongoClient mongoclient, MongoTemplate mongoTemplate) {
        this.mongoclient = mongoclient;
        this.mongoTemplate = mongoTemplate;
    }

    @PostConstruct
    void init() {
        saleCollection = mongoclient.getDatabase("sample_supplies").getCollection("sales", Sales.class);
    }

    @Override
    public List<Sales> findAll() {
        return saleCollection.find().into(new ArrayList<>());
    }

    @Override
    public Sales update(Sales sale) {
        FindOneAndReplaceOptions options = new FindOneAndReplaceOptions().returnDocument(AFTER);
        return saleCollection.findOneAndReplace(eq("_id", sale.getId()), sale, options);
    }

    @Override
    public Sales findOne(String id) {
        return saleCollection.find(eq("_id", new ObjectId(id))).first();
    }

    @Override
    public Long delete(String id) {
        return saleCollection.deleteOne(eq("_id", new ObjectId(id))).getDeletedCount();
    }

    @Override
    public List<SalesDTO> matchOp(String matchValue) {
        MatchOperation matchStage = match(new Criteria("storeLocation").is(matchValue));
        ProjectionOperation projectStage = project("items");
        Aggregation aggregation = newAggregation(matchStage, projectStage);
        AggregationResults<SalesDTO> results = mongoTemplate.aggregate(aggregation, "sales", SalesDTO.class);
        return results.getMappedResults();
    }

    @Override
    public List<GroupDTO> groupOp(String matchValue) {
        MatchOperation matchStage = match(new Criteria("storeLocation").is(matchValue));
        GroupOperation groupStage = group("storeLocation").count()
                                                          .as("totalSales")
                                                          .avg("customer.satisfaction")
                                                          .as("averageSatisfaction");
        ProjectionOperation projectStage = project("storeLocation", "totalSales", "averageSatisfaction");
        Aggregation aggregation = newAggregation(matchStage, groupStage, projectStage);
        AggregationResults<GroupDTO> results = mongoTemplate.aggregate(aggregation, "sales", GroupDTO.class);
        return results.getMappedResults();
    }

    @Override
    public List<TotalSalesDTO> TotalSales() {
        GroupOperation groupStage = group("storeLocation").count().as("totalSales");
        SkipOperation skipStage = skip(0);
        LimitOperation limitStage = limit(10);
        Aggregation aggregation = newAggregation(groupStage, skipStage, limitStage);
        AggregationResults<TotalSalesDTO> results = mongoTemplate.aggregate(aggregation, "sales", TotalSalesDTO.class);
        return results.getMappedResults();
    }

    @Override
    public List<PopularDTO> findPopularItems() {
        UnwindOperation unwindStage = unwind("items");
        GroupOperation groupStage = group("$items.name").sum("items.quantity").as("totalQuantity");
        SortOperation sortStage = sort(Sort.Direction.DESC, "totalQuantity");
        LimitOperation limitStage = limit(5);
        Aggregation aggregation = newAggregation(unwindStage,groupStage, sortStage, limitStage);
        return mongoTemplate.aggregate(aggregation, "sales", PopularDTO.class).getMappedResults();
    }
    @Override
    public List<BucketsDTO> findTotalSpend(){
        ProjectionOperation projectStage = Aggregation.project()
                .and(ArrayOperators.Size.lengthOfArray("items")).as("numItems")
                .and(ArithmeticOperators.Multiply
                        .valueOf("item.price")
                        .multiplyBy("item.quantity")
                ).as("totalAmount");

        BucketOperation bucketStage = Aggregation.bucket("numItems")
                .withBoundaries(0, 3, 6, 9)
                .withDefaultBucket("Other")
                .andOutputCount().as("count")
                .andOutput("totalAmount").sum().as("totalAmount");

        Aggregation aggregation = newAggregation(projectStage, bucketStage);
        return mongoTemplate.aggregate(aggregation, "sales", BucketsDTO.class).getMappedResults();
    }
}
