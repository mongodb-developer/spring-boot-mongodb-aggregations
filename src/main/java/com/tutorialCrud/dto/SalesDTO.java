package com.tutorialCrud.dto;

import com.tutorialCrud.model.Sales;
import org.bson.types.ObjectId;

import java.util.Date;
import java.util.List;

public record SalesDTO(String id, Date saleDate, String storeLocation, Boolean couponUsed, String purchaseMethod,
                       CustomerDTO customer, List<ItemDTO> items) {

    public SalesDTO(Sales s) {
        this(s.getId() == null ? new ObjectId().toHexString() : s.getId().toHexString(), s.getSaleDate(),
                s.getStoreLocation(), s.isCouponUsed(), s.getPurchaseMethod(), new CustomerDTO((s.getCustomer())),
                s.getItems().stream().map(ItemDTO::new).toList());
    }

    public Sales toSales() {
        ObjectId _id = id == null ? new ObjectId() : new ObjectId(id);
        return new Sales(_id, saleDate, items.stream().map(ItemDTO::toItem).toList(), storeLocation,
                customer.toCustomer(), couponUsed, purchaseMethod);

    }
}