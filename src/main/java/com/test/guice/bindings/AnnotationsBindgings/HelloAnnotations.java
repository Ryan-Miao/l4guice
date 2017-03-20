package com.test.guice.bindings.AnnotationsBindgings;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.test.guice.bindings.AnnotationsBindgings.service.City;
import com.test.guice.bindings.AnnotationsBindgings.service.IPersonSay;

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
