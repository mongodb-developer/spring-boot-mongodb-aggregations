package com.tutorialCrud.controller;

import com.tutorialCrud.dto.*;
import com.tutorialCrud.exceptions.EntityNotFoundException;
import com.tutorialCrud.model.Sales;
import com.tutorialCrud.service.SalesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/sales")
public class SalesController {

    private final static Logger LOGGER = LoggerFactory.getLogger(SalesController.class);
    private final SalesService salesService;

    public SalesController(SalesService salesService) {
        this.salesService = salesService;
    }
    @PostMapping("person")
    @ResponseStatus(HttpStatus.CREATED)
    public SalesDTO postPerson(@RequestBody SalesDTO SalesDTO) {
        return salesService.save(SalesDTO);
    }
    @GetMapping()
    public List<SalesDTO> getAllSales() {
        return salesService.findAll();
    }
    @GetMapping("/{id}")
    public Sales getSalesById(@PathVariable String id) {
        return salesService.findOne(id);
    }
    @PutMapping("/updateUser")
    public Sales updateSale(@RequestBody SalesDTO salesDTO) {
        return salesService.updateSale(salesDTO);
    }
    @DeleteMapping("/deleteUser/{id}")
    public Long deleteSale(@PathVariable String id) {
        return salesService.deleteSale(id);
    }

    //Find all documents with the storeLocation specified. Example: London
    @GetMapping("/aggregation/{matchStage}")
    public List<SalesDTO> matchAggregationOp(@PathVariable String matchStage) {
        return salesService.matchAggregationOp(matchStage);
    }

    //filters documents based on the "storeLocation", groups them by "storeLocation", calculates the total sales and average satisfaction for each store location,
    @GetMapping("/aggregation/groupStage/{matchStage}")
    public List<GroupDTO> groupAggregationOp(@PathVariable String matchStage) {
        return salesService.groupAggregation(matchStage);
    }

    //Count the total sales made in each store location:
    @GetMapping("/aggregation/TotalSales")
    public List<TotalSalesDTO> findTotalSales() {
        return salesService.findTotalSales();
    }

    //Finding the top 5 sold items based on the quantity sold for each category
    @GetMapping("/aggregation/PopularItem")
    public List<PopularDTO> findPopularItems() { return salesService.findPopularItems(); }

    //This MongoDB aggregation pipeline first calculates the number of items in each sale
    // and the total amount spent for each sale.
    // Then it buckets the sales based on the number of items, calculates the count
    // and total amount spent within each bucket.
    @GetMapping("/aggregation/buckets")
    public List<BucketsDTO> findTotalSpend(){ return salesService.findTotalSpend(); }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "MongoDB didn't find any document.")
    public final void handleNotFoundExceptions(EntityNotFoundException e) {
        LOGGER.info("=> No data Found {}", e.toString());
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Internal Server Error")
    public final void handleAllExceptions(RuntimeException e) {
        LOGGER.error("=> Internal server error.", e);
    }
}
