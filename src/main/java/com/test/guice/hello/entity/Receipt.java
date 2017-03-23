package com.test.guice.hello.entity;

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

    public Status getStatus() {
        return status;
    }


    @Override
    public String toString() {
        return "Receipt{" +
                "amount=" + amount +
                ", status=" + status +
                ", message='" + message + '\'' +
                '}';
    }

    public enum Status{
        SUCCESS, FAILED, DECLINE
    }
}
