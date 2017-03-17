package com.test.guice.hello;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.test.guice.hello.entity.CreditCard;
import com.test.guice.hello.entity.PizzaOrder;
import com.test.guice.hello.entity.Receipt;
import com.test.guice.hello.service.BillingService;
import com.test.guice.hello.service.impl.RealBillingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.Properties;

/**
 * Created by rmiao on 3/17/2017.
 */
public class BillingModule extends AbstractModule {
    private static final Logger LOGGER = LoggerFactory.getLogger(BillingModule.class);

    protected void configure() {
        bind(BillingService.class).to(RealBillingService.class);
    }

    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new BillingModule());
        BillingService billingService = injector.getInstance(BillingService.class);
        Receipt receipt = billingService.chargeOrder(new PizzaOrder(new BigDecimal(100)),
                new CreditCard("43012121"));
        LOGGER.info("result {}", receipt);

        receipt = billingService.chargeOrder(new PizzaOrder(null),
                new CreditCard("43012121"));
        LOGGER.info("result {}", receipt);

    }
}
