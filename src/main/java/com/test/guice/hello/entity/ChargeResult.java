package com.test.guice.hello.entity;

/**
 * Created by rmiao on 3/17/2017.
 */
public class ChargeResult {
    private boolean successful;
    private String declineMessage;

    public ChargeResult(boolean successful) {
        this.successful = successful;
    }


    public boolean wasSuccessful() {

        return successful;
    }


    public boolean isSuccessful() {
        return successful;
    }

    public String getDeclineMessage() {
        return declineMessage;
    }

    @Override
    public String toString() {
        return "ChargeResult{" +
                "successful=" + successful +
                ", declineMessage='" + declineMessage + '\'' +
                '}';
    }
}
