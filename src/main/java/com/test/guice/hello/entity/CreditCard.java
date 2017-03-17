package com.test.guice.hello.entity;

/**
 * Created by rmiao on 3/17/2017.
 */
public class CreditCard {

    private String no;

    public CreditCard(String no) {
        this.no = no;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }
}
