package com.test.guice.bindings.AnnotationsBindgings;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.test.guice.bindings.AnnotationsBindgings.service.City;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by rmiao on 3/20/2017.
 */
public class HelloAnnotationsTest {

    private HelloAnnotations instance;
    @Before
    public void setUp() throws Exception {
        Injector injector = Guice.createInjector(new AnnotationBindingModule());
        instance = injector.getInstance(HelloAnnotations.class);

    }

    @Test
    public void say() throws Exception {
        City say = instance.say();
        assertEquals(City.SHEN_ZHEN, say);
    }

}