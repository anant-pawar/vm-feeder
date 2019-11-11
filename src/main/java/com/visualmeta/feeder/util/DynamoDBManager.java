package com.visualmeta.feeder.util;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

public class DynamoDBManager {
    private static volatile DynamoDBManager instance;
    private static DynamoDBMapper mapper;

    private DynamoDBManager() {
        mapper = new DynamoDBMapper(
                AmazonDynamoDBClientBuilder
                        .standard()
                        .withRegion(Regions.EU_CENTRAL_1)
                        .defaultClient());
    }

    public static DynamoDBManager instance() {

        if (instance == null) {
            synchronized (DynamoDBManager.class) {
                if (instance == null)
                    instance = new DynamoDBManager();
            }
        }

        return instance;
    }

    public static DynamoDBMapper mapper() {
        return instance().mapper;
    }
}
