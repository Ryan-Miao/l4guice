package com.test.guice.hello.service.impl;

import com.google.inject.Inject;
import com.test.guice.hello.entity.ChargeResult;
import com.test.guice.hello.entity.CreditCard;
import com.test.guice.hello.entity.PizzaOrder;
import com.test.guice.hello.entity.Receipt;
import com.test.guice.hello.exception.UnreachableException;
import com.test.guice.hello.logger.TransactionLog;
import com.test.guice.hello.processor.CreditCardProcessor;
import com.test.guice.hello.service.BillingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by rmiao on 3/17/2017.
 */
public class RealBillingService implements BillingService {
    private static  final Logger LOGGER = LoggerFactory.getLogger(RealBillingService.class);

    private final CreditCardProcessor processor;
    private final TransactionLog transactionLog;

    @Inject
    public RealBillingService(CreditCardProcessor processor,
                              TransactionLog transactionLog) {
        this.processor = processor;
        this.transactionLog = transactionLog;
        LOGGER.info("real billing service init.");
    }

    public Receipt chargeOrder(PizzaOrder order, CreditCard creditCard) {
        try {
            ChargeResult result = processor.charge(creditCard, order.getAmount());
            transactionLog.logChargeResult(result);

            return result.wasSuccessful()
                    ? Receipt.forSuccessfulCharge(order.getAmount())
                    : Receipt.forDeclinedCharge(result.getDeclineMessage());
        } catch (UnreachableException e) {
            transactionLog.logConnectException(e);
            return Receipt.forSystemFailure(e.getMessage());
        }
    }
}
