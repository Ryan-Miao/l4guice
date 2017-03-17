package com.test.guice.hello.service.impl;

import com.test.guice.hello.entity.ChargeResult;
import com.test.guice.hello.entity.CreditCard;
import com.test.guice.hello.entity.PizzaOrder;
import com.test.guice.hello.entity.Receipt;
import com.test.guice.hello.exception.UnreachableException;
import com.test.guice.hello.logger.TransactionLog;
import com.test.guice.hello.processor.CreditCardProcessor;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * Created by Ryan on 2017/3/17/017.
 */
@RunWith(MockitoJUnitRunner.class)
public class RealBillingServiceTest {

    @InjectMocks
    RealBillingService realBillingService;
    @Mock
    CreditCardProcessor processor;
    @Mock
    TransactionLog transactionLog;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void chargeOrderHappyPath() throws Exception {
        final ChargeResult result = Mockito.mock(ChargeResult.class);
        final PizzaOrder order = Mockito.mock(PizzaOrder.class);
        final CreditCard card = Mockito.mock(CreditCard.class);

        Mockito.when(processor.charge(Mockito.any(CreditCard.class),
                Mockito.any(BigDecimal.class)))
                .thenReturn(result);
        Mockito.doAnswer(invocation -> null)
                .when(transactionLog).logChargeResult(Mockito.any(ChargeResult.class));
        Mockito.when(result.wasSuccessful())
                .thenReturn(true);
        Mockito.when(order.getAmount())
                .thenReturn(new BigDecimal(1));

        Receipt receipt = realBillingService.chargeOrder(order, card);

        Assert.assertNotNull(receipt);
        Assert.assertEquals(Receipt.Status.SUCCESS, receipt.getStatus());
        Mockito.verify(processor).charge(Mockito.any(CreditCard.class),
                Mockito.any(BigDecimal.class));
        Mockito.verify(transactionLog).logChargeResult(Mockito.any(ChargeResult.class));

    }

    @Test
    public void chargeOrderDecliend() throws Exception {
        final ChargeResult result = Mockito.mock(ChargeResult.class);
        final PizzaOrder order = Mockito.mock(PizzaOrder.class);
        final CreditCard card = Mockito.mock(CreditCard.class);

        Mockito.when(processor.charge(Mockito.any(CreditCard.class),
                Mockito.any(BigDecimal.class)))
                .thenReturn(result);
        Mockito.doAnswer(invocation -> null)
                .when(transactionLog).logChargeResult(Mockito.any(ChargeResult.class));
        Mockito.when(result.wasSuccessful())
                .thenReturn(false);
        Mockito.when(result.getDeclineMessage())
                .thenReturn("The amount is not equal.");
        Mockito.when(order.getAmount())
                .thenReturn(new BigDecimal(1));

        Receipt receipt = realBillingService.chargeOrder(order, card);

        Assert.assertNotNull(receipt);
        Assert.assertEquals(Receipt.Status.DECLINE, receipt.getStatus());
        Mockito.verify(processor).charge(Mockito.any(CreditCard.class),
                Mockito.any(BigDecimal.class));
        Mockito.verify(transactionLog).logChargeResult(Mockito.any(ChargeResult.class));

    }

    @Test
    public void chargeOrderThrowException() throws UnreachableException {
        final PizzaOrder order = Mockito.mock(PizzaOrder.class);
        final CreditCard card = Mockito.mock(CreditCard.class);

        Mockito.when(order.getAmount())
                .thenReturn(new BigDecimal(1));
        Mockito.when(processor.charge(Mockito.any(CreditCard.class),
                Mockito.any(BigDecimal.class)))
                .thenThrow(new UnreachableException("call error."));
        Mockito.doAnswer(invocation -> null)
                .when(transactionLog).logConnectException(Mockito.any(UnreachableException.class));

        Receipt receipt = realBillingService.chargeOrder(order, card);
        Mockito.verify(transactionLog)
                .logConnectException(Mockito.any(UnreachableException.class));
        Assert.assertEquals(Receipt.Status.FAILED, receipt.getStatus());
    }


}