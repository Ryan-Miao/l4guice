package com.test.guice.bindings.InstanceBindings;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

/**
 * Created by rmiao on 3/23/2017.
 */
public class ConfigModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(String.class)
                .annotatedWith(Names.named("jdbc.url"))
                .toInstance("jdbc:mysql://localhost/pizza");
        bind(Integer.class)
                .annotatedWith(Names.named("jdbc.timeout"))
                .toInstance(10);
    }

}
