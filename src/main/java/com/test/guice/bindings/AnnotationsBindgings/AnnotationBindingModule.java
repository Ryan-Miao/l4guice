package com.test.guice.bindings.AnnotationsBindgings;

import com.google.inject.AbstractModule;
import com.test.guice.bindings.service.Chinese;
import com.test.guice.bindings.service.IPersonSay;
import com.test.guice.bindings.service.impl.ShenZhenSay;

/**
 * Created by rmiao on 3/20/2017.
 */
public class AnnotationBindingModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(IPersonSay.class)
                .annotatedWith(Chinese.class)
                .to(ShenZhenSay.class);
    }
}
