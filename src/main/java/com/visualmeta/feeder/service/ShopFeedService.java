package com.visualmeta.feeder.service;

import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.baidu.unbiz.fluentvalidator.Result;
import com.visualmeta.feeder.model.Message;
import com.visualmeta.feeder.model.Product;
import com.visualmeta.feeder.model.ProductDocument;
import com.visualmeta.feeder.model.ProductField;
import com.visualmeta.feeder.repository.ShopFeedRepository;
import com.visualmeta.feeder.validator.NotNullValidator;

import java.util.UUID;

import static com.baidu.unbiz.fluentvalidator.ResultCollectors.toSimple;

public class ShopFeedService {
    private ShopFeedRepository shopFeedRepository;

    public ShopFeedService(ShopFeedRepository shopFeedRepository) {
        this.shopFeedRepository = shopFeedRepository;
    }

    public Message createProduct(Product product) {
        Result validationResult = FluentValidator
                .checkAll()
                .failOver()
                .on(product.getId(), new NotNullValidator(ProductField.ID))
                .on(product.getName(), new NotNullValidator(ProductField.NAME))
                .doValidate()
                .result(toSimple());

        if (!validationResult.isSuccess()) {
            return new Message(Message.StatusCode.FAILED, validationResult.toString());
        } else {
            try {
                this.shopFeedRepository.saveProduct(new ProductDocument(UUID.randomUUID().toString(), product));
            } catch (Exception exception) {
                return new Message(Message.StatusCode.FAILED, exception.getMessage());
            }
            return new Message(Message.StatusCode.OK);
        }
    }

}
