package com.test.guice.bindings.ProvidesBindings;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.name.Named;
import com.google.inject.name.Names;
import com.test.guice.bindings.logger.TransactionLog;
import com.test.guice.bindings.logger.impl.MySqlDatabaseTrannsactionLog;
import com.test.guice.bindings.service.Chinese;
import com.test.guice.bindings.service.IPersonSay;
import com.test.guice.bindings.service.impl.NewYorkSay;
import com.test.guice.bindings.service.impl.ShenZhenSay;

/**
 * Created by rmiao on 3/23/2017.
 */
public class ObjectInstanceModule extends AbstractModule {

    public static final String JDBC_URL = "jdbc:mysql://localhost/pizaa";
    public static final Integer THREAD_POOL_SIZE = 30;

    @Override
    protected void configure() {
        bind(String.class)
                .annotatedWith(Names.named("jdbc.url"))
                .toInstance(JDBC_URL);

        bind(IPersonSay.class)
                .to(NewYorkSay.class);
    }

    @Provides
    TransactionLog provideTransactionLog(@Named("jdbc.url")String url){
        return new MySqlDatabaseTrannsactionLog(
                url, THREAD_POOL_SIZE);
    }

    @Provides @Chinese
    IPersonSay providePerson(){
        return new ShenZhenSay();
    }

}
