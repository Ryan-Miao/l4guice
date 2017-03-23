package com.test.guice.bindings.InstanceBindings;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.test.guice.bindings.AnnotationsBindgings.AnnotationBindingModule;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by rmiao on 3/23/2017.
 */
public class ConfigModuleTest {
    private ConfigBind configBind;

    @Before
    public void setUp() throws Exception {

        Injector injector = Guice.createInjector(new ConfigModule());
        configBind = injector.getInstance(ConfigBind.class);

    }

    @Test
    public void testBind(){
        boolean connect = configBind.connect();
        assertTrue(connect);
    }

}