package com.visualmeta.feeder.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;

import java.util.Locale;

@DynamoDBDocument
public class Size {
    private Locale locale;
    private String value;

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
