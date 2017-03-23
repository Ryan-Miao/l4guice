package com.test.guice.bindings.ProvidesBindings;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.test.guice.bindings.InstanceBindings.ConfigModule;
import com.test.guice.bindings.logger.TransactionLog;
import com.test.guice.bindings.service.City;
import com.test.guice.bindings.service.HelloAnnotations;
import com.test.guice.bindings.service.IPersonSay;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by rmiao on 3/23/2017.
 */
public class ObjectInstanceModuleTest {
    private TransactionLog log;
    private IPersonSay personSay;
    private HelloAnnotations helloAnnotations;

    @Before
    public void setUp() throws Exception {
        Injector injector = Guice.createInjector(new ObjectInstanceModule());
        log = injector.getInstance(TransactionLog.class);
        personSay = injector.getInstance(IPersonSay.class);
        helloAnnotations = injector.getInstance(HelloAnnotations.class);
    }

    @Test
    public void testBindWithProvides(){
        String url = log.getUrl();
        Integer threadPoolSize = log.getThreadPoolSize();
        assertEquals(ObjectInstanceModule.JDBC_URL, url);
        assertEquals(ObjectInstanceModule.THREAD_POOL_SIZE, threadPoolSize);

    }

    @Test
    public void testBindMultiple(){
        City say = personSay.say();
        assertEquals(City.NEW_YORK, say);

        City hello = helloAnnotations.say();
        assertEquals(City.SHEN_ZHEN, hello);
    }

}