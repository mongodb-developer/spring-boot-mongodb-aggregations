package com.tutorialCrud.repository;

import com.mongodb.ReadConcern;
import com.mongodb.ReadPreference;
import com.mongodb.TransactionOptions;
import com.mongodb.WriteConcern;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.FindOneAndReplaceOptions;
import com.tutorialCrud.model.Sales;
import jakarta.annotation.PostConstruct;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.ReturnDocument.AFTER;
@Repository
public class MongoDBSaleRepository implements  SalesRepository{

    private final MongoClient mongoclient;
    private MongoCollection<Sales> saleCollection;

    public MongoDBSaleRepository(MongoClient mongoclient) {
        this.mongoclient = mongoclient;
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
        return saleCollection.findOneAndReplace(eq("_id", Sales.getId()), sale, options);
    }

    @Override
    public Sales findOne(String id) {
        return saleCollection.find(eq("_id", new ObjectId(id))).first();
    }

    @Override
    public Long delete(String id) {
        return saleCollection.deleteOne(eq("_id", new ObjectId(id))).getDeletedCount();
    }
}
