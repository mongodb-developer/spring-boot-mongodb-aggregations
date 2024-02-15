package com.tutorialCrud.repository;

import com.tutorialCrud.model.Sales;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface SalesRepository extends MongoRepository<Sales, String> {

    List<Sales> findByItemsTagsContaining(String tag);

    List<Sales> findByPurchaseMethod(String purchaseMethod);

    @Query(value = "{ 'storeLocation' : ?0 }")
    Sales findByStoreLocation(String storeLocation);

}
