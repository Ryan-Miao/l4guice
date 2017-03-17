package com.test.guice.hello.entity;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.math.BigDecimal;

/**
 * Created by rmiao on 3/17/2017.
 */
public class Receipt {
    private BigDecimal amount;
    private Status status;
    private String message;

    public Receipt(BigDecimal amount) {
        this.amount = amount;
        this.status = Status.SUCCESS;
    }

    public Receipt(String message, Status status) {
        this.message = message;
        this.status = status;
    }

    public static Receipt forSuccessfulCharge(BigDecimal amount) {
        return new Receipt(amount);
    }

    public static Receipt forDeclinedCharge(String declineMessage) {
        return new Receipt(declineMessage, Status.DECLINE);
    }

    public static Receipt forSystemFailure(String message) {
        return new Receipt(message, Status.FAILED);
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Receipt{" +
                "amount=" + amount +
                ", status=" + status +
                ", message='" + message + '\'' +
                '}';
    }

    public static enum Status{
        SUCCESS, FAILED, DECLINE
    }
}
