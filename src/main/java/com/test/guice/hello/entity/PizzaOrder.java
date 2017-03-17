package com.test.guice.hello.entity;

import java.math.BigDecimal;

/**
 * Created by rmiao on 3/17/2017.
 */
public class PizzaOrder {
    private BigDecimal amount;

    public PizzaOrder(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}
