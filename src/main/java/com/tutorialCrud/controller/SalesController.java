package com.tutorialCrud.controller;

import com.tutorialCrud.model.Sales;
import com.tutorialCrud.service.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/sales")
public class SalesController {

    @Autowired
    private SalesService salesService;

    @GetMapping
    public List<Sales> getAllSales() {
        return salesService.getAllSales();
    }

    @GetMapping("/{id}")
    public Sales getSaleById(@PathVariable String id) {
        return salesService.getSaleById(id);
    }

    @PostMapping
    public Sales createSale(@RequestBody Sales sale) {
        return salesService.createSale(sale);
    }

    @PutMapping("/{id}")
    public Sales updateSale(@PathVariable String id, @RequestBody Sales sale) {
        return salesService.updateSale(id, sale);
    }

    @DeleteMapping("/{id}")
    public void deleteSale(@PathVariable String id) {
        salesService.deleteSale(id);
    }

    @GetMapping("/totalAmount")
    public BigDecimal getTotalSalesAmount() {
        return salesService.getTotalSalesAmount();
    }

    @GetMapping(params = "tag")
    public List<Sales> getSalesByTag(@RequestParam String tag) {
        return salesService.getSalesByTag(tag);
    }

    @GetMapping(params = "purchaseMethod")
    public List<Sales> getSalesByPurchaseMethod(@RequestParam String purchaseMethod) {
        return salesService.getSalesByPurchaseMethod(purchaseMethod);
    }

    @GetMapping("/singleSale")
    public Stream<Sales> getSingleSale() {
        return salesService.getSingleSale();
    }

    @GetMapping("/sale/{location}")
    public Sales getSaleByLocation(@PathVariable String location) {
        return salesService.getSaleByLocation(location);
    }

    @GetMapping("/aggregation/{matchStage}")
    public List<Sales> matchAggregationOp(@PathVariable String matchStage) {
        return salesService.matchAggregationOp(matchStage);
    }

    @GetMapping("/aggregation/groupStage/{matchStage}")
    public List<Map> groupAggregationOp(@PathVariable String matchStage) {
        return salesService.groupAggregation(matchStage);
    }
}
