package com.test.guice.bindings.service.impl;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.test.guice.bindings.AnnotationsBindgings.AnnotationBindingModule;
import com.test.guice.bindings.service.City;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by rmiao on 3/20/2017.
 */
public class NewYorkSayTest {
    private NewYorkSay newYorkSay;
    @Before
    public void setUp() throws Exception {
        Injector injector = Guice.createInjector(new AnnotationBindingModule());
        newYorkSay = injector.getInstance(NewYorkSay.class);
    }

    @Test
    public void shouldGetAInstanceThoughDoNotNeedInject() throws Exception {
        City say = newYorkSay.say();
        assertEquals(City.NEW_YORK, say);
    }

}