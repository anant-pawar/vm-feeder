package com.visualmeta.feeder.error;

import com.baidu.unbiz.fluentvalidator.Result;

public class ShopFeedValidationException extends Exception {
    private Result result;

    public ShopFeedValidationException( Result result){
        this.result = result;
    }

    public Result getResult() {
        return result;
    }
}
