package com.visualmeta.feeder.model;

public enum ProductField {
    ID("id"),
    NAME("name");

    private String field;

    private ProductField(String field){
        this.field = field;
    }
}
