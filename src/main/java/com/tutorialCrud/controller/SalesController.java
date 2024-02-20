package com.tutorialCrud.controller;

import com.tutorialCrud.Dto.SalesDTO;
import com.tutorialCrud.model.Sales;
import com.tutorialCrud.service.SalesService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/sales")
public class SalesController {

    private final static Logger LOGGER = LoggerFactory.getLogger(SalesController.class);
    private final SalesService salesService;
    public SalesController(SalesService salesService) {
        this.salesService = salesService;
    }

    @GetMapping()
    public List<SalesDTO> getAllSales() {
        return salesService.findAll();
    }

    @GetMapping("/findOne/{id}")
   public ResponseEntity<SalesDTO> getSalesById(@PathVariable String id){
        SalesDTO sales = salesService.findOne(id);
        if (sales == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.ok(sales);
    }


    @PutMapping("/updateUser")
    public SalesDTO updateSale(@RequestBody SalesDTO salesDTO) {
        return salesService.updateSale(salesDTO);
    }
    @DeleteMapping("/deleteUser/{id}")
    public Long deleteSale(@PathVariable String id) {
        return salesService.deleteSale(id);
    }

    //Find all documents with the storeLocation specified. Example: London
    @GetMapping("/aggregation/{matchStage}")
    public List<Sales> matchAggregationOp(@PathVariable String matchStage) {
        return salesService.matchAggregationOp(matchStage);
    }


    //filters documents based on the "storeLocation", groups them by "storeLocation", calculates the total sales and average satisfaction for each store location,
    @GetMapping("/aggregation/groupStage/{matchStage}")
    public List<Map> groupAggregationOp(@PathVariable String matchStage) {
        return salesService.groupAggregation(matchStage);
    }

   //Count the number of sales made in each store location:
    @GetMapping("/aggregation/TotalSales")
    public List<Map> findTotalSales(){
        return salesService.findTotalSales();
    }
}
