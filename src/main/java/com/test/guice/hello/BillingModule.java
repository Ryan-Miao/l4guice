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

}
