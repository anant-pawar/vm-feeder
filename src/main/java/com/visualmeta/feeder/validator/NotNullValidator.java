package com.visualmeta.feeder.validator;

import com.baidu.unbiz.fluentvalidator.Validator;
import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.visualmeta.feeder.model.ProductField;

public  class NotNullValidator extends ValidatorHandler<String> implements Validator<String> {


    private ProductField productField;

    public NotNullValidator(ProductField productField){
        this.productField = productField;
    }

    @Override
    public boolean validate(ValidatorContext context, String field) {
        if (field == null || field.isEmpty()) {
            context.addErrorMsg(this.productField.toString()  + " cannot be empty");
            return false;
        }

        return true;
    }

}
