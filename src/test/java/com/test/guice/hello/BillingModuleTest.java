package com.test.guice.hello;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.test.guice.hello.entity.CreditCard;
import com.test.guice.hello.entity.PizzaOrder;
import com.test.guice.hello.entity.Receipt;
import com.test.guice.hello.service.BillingService;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * Created by rmiao on 3/20/2017.
 */
public class BillingModuleTest {
    private BillingService billingService;
    @Before
    public void setUp() throws Exception {
        Injector injector = Guice.createInjector(new BillingModule());
        billingService = injector.getInstance(BillingService.class);
    }

    @Test
    public void testHello(){

        Receipt receipt = billingService.chargeOrder(new PizzaOrder(new BigDecimal(100)),
                new CreditCard("43012121"));
        assertEquals(Receipt.Status.SUCCESS, receipt.getStatus());

        receipt = billingService.chargeOrder(new PizzaOrder(null),
                new CreditCard("43012121"));
        assertEquals(Receipt.Status.FAILED, receipt.getStatus());
    }

}