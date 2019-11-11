package com.visualmeta.feeder.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.visualmeta.feeder.model.ProductDocument;
import com.visualmeta.feeder.util.DynamoDBManager;

public class ShopFeedRepository {
    private final DynamoDBMapper mapper;

    public ShopFeedRepository(){
        this.mapper = DynamoDBManager.mapper();

    }
    public void saveProduct(ProductDocument productDocument){
        mapper.save(productDocument);
    }
}
