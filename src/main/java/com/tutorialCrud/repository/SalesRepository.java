package com.tutorialCrud.repository;

import com.tutorialCrud.model.Sales;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Collection;
import java.util.List;
public interface SalesRepository{

   List<Sales> findAll();

    Sales update(Sales sale);

    Sales findOne(String id);

    Long delete(String id);
}
