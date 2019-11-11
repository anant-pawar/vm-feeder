package com.visualmeta.feeder.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;

import java.util.Currency;

@DynamoDBDocument
public class Price {
    private Currency currency;
    private Integer value;

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
