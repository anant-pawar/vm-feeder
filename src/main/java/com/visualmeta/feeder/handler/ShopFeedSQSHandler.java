package com.visualmeta.feeder.handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.visualmeta.feeder.model.Message;
import com.visualmeta.feeder.model.Product;
import com.visualmeta.feeder.model.request.Record;
import com.visualmeta.feeder.model.request.RecordBatch;
import com.visualmeta.feeder.repository.ShopFeedRepository;
import com.visualmeta.feeder.service.ShopFeedService;

import java.io.IOException;
import java.util.Map;

public class ShopFeedSQSHandler implements RequestHandler<Map, Void> {

    private ObjectMapper objectMapper = new ObjectMapper();
    private ShopFeedService shopFeedService = new ShopFeedService(new ShopFeedRepository());

    public Void handleRequest(Map data, Context context) {
        context.getLogger().log(data.toString());

        try {
            RecordBatch recordBatch = objectMapper.readValue(
                    objectMapper.writeValueAsBytes(data), RecordBatch.class);

            for (Record record : recordBatch.getRecords()) {
                Product product = objectMapper.readValue(
                        record.getBody().toString(), Product.class);

                Message message = shopFeedService.createProduct(product);

                if (message.getStatus().equals(Message.StatusCode.FAILED))
                    context.getLogger().log(message.getMessage());
                else
                    context.getLogger().log(product.getId());
            }
        } catch (IOException exception) {
            context.getLogger().log(exception.getMessage());
        }

        return null;
    }
}