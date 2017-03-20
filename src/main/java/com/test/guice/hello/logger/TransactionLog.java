package com.test.guice.hello.logger;

import com.test.guice.hello.entity.ChargeResult;
import com.test.guice.hello.exception.UnreachableException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by rmiao on 3/17/2017.
 */
public class TransactionLog {
    private static  final Logger LOGGER = LoggerFactory.getLogger(TransactionLog.class);

    public void logChargeResult(ChargeResult result) {
        if (result == null) {
            LOGGER.error("Charge failed. result is null.");
            return;
        }

        if (result.isSuccessful()){
            LOGGER.info("Charge success. result[{}]", result);
        }else{
            LOGGER.error("Charge failed. result[{}]", result);
        }
    }

    public void logConnectException(UnreachableException e) {
        System.out.println(e.getMessage());
    }
}
