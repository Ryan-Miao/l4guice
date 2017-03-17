package com.test.guice.hello.service;

import com.test.guice.hello.entity.CreditCard;
import com.test.guice.hello.entity.PizzaOrder;
import com.test.guice.hello.entity.Receipt;

/**
 * Created by rmiao on 3/17/2017.
 */
public interface BillingService {
    /**
     * Attempts to charge the order to the credit card. Both successful and
     * failed transactions will be recorded.
     *
     * @return a receipt of the transaction. If the charge was successful, the
     *      receipt will be successful. Otherwise, the receipt will contain a
     *      decline note describing why the charge failed.
     */
    Receipt chargeOrder(PizzaOrder order, CreditCard creditCard);
}
