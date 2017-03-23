package com.test.guice.hello.processor;

import com.test.guice.hello.entity.ChargeResult;
import com.test.guice.hello.entity.CreditCard;
import com.test.guice.hello.exception.UnreachableException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

/**
 * Created by rmiao on 3/17/2017.
 */
public class CreditCardProcessor {
    private static final Logger LOGGER = LoggerFactory.getLogger(CreditCardProcessor.class);

    private static volatile int count;

    public CreditCardProcessor() {
        count ++;
        LOGGER.info("credit card processor init [{}]", count);
    }

    public ChargeResult charge(CreditCard creditCard, BigDecimal amount) throws UnreachableException {
        String validate = validate(creditCard, amount);
        if (StringUtils.isNotBlank(validate)){
            throw new UnreachableException("amount can not be null");
        }

        LOGGER.info("credit[{}] changed ${} ", creditCard.getNo(), amount.doubleValue());

        return new ChargeResult(true);
    }

    private String validate(CreditCard creditCard, BigDecimal amount) {
        String msg = null;
//        if (creditCard == null ){
//            msg = "credit card can not be null.";
//        }else if(StringUtils.isBlank(creditCard.getNo())){
//            msg = "credit no can not be empty";
//        }

        if (amount == null){
            msg = "Amount can not be null.";
        }

        return msg;
    }
}
