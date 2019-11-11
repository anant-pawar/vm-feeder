package com.visualmeta.feeder.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class RecordBatch {
    @JsonProperty(value = "Records")
    private List<Record> records;

    public List<Record> getRecords() {
        return records;
    }

    public void setRecords(List<Record> records) {
        this.records = records;
    }
}
