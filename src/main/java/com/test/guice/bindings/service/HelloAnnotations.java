package com.test.guice.bindings.service;

import com.google.inject.Inject;

/**
 * Created by rmiao on 3/20/2017.
 */
public class HelloAnnotations {

    private IPersonSay personSay;

    @Inject
    public HelloAnnotations(@Chinese IPersonSay personSay) {
        this.personSay = personSay;
    }

    public City say() {
        return personSay.say();
    }

}
