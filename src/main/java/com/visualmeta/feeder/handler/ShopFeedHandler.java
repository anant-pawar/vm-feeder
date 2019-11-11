package com.visualmeta.feeder.handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.visualmeta.feeder.model.Message;
import com.visualmeta.feeder.model.Product;
import com.visualmeta.feeder.repository.ShopFeedRepository;
import com.visualmeta.feeder.service.ShopFeedService;

public class ShopFeedHandler implements RequestHandler<Product, Message> {
    private ShopFeedService shopFeedService = new ShopFeedService(new ShopFeedRepository());

    public Message handleRequest(Product product, Context context) {
        Message message = shopFeedService.createProduct(product);

        if (message.getStatus().equals(Message.StatusCode.FAILED))
            context.getLogger().log(message.getMessage());
        else
            context.getLogger().log(product.getId());

        return message;
    }
}
