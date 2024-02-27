package com.tutorialCrud.controller;

import com.tutorialCrud.dto.GroupDTO;
import com.tutorialCrud.dto.SalesDTO;
import com.tutorialCrud.dto.TotalSalesDTO;
import com.tutorialCrud.exceptions.EntityNotFoundException;
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

    @GetMapping()
    public List<SalesDTO> getAllSales() {
        return salesService.findAll();
    }

    @GetMapping("/{id}")
    public SalesDTO getSalesById(@PathVariable String id) {
        return salesService.findOne(id);
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
    public List<SalesDTO> matchAggregationOp(@PathVariable String matchStage) {
        return salesService.matchAggregationOp(matchStage);
    }

    //filters documents based on the "storeLocation", groups them by "storeLocation", calculates the total sales and average satisfaction for each store location,
    @GetMapping("/aggregation/groupStage/{matchStage}")
    public List<GroupDTO> groupAggregationOp(@PathVariable String matchStage) {
        return salesService.groupAggregation(matchStage);
    }

    //Count the number of sales made in each store location:
    @GetMapping("/aggregation/TotalSales")
    public List<TotalSalesDTO> findTotalSales() {
        return salesService.findTotalSales();
    }

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
