package com.test.guice.bindings.LinkedBindings;

import com.google.inject.AbstractModule;
import com.test.guice.bindings.logger.TransactionLog;
import com.test.guice.bindings.logger.DatabaseTransactionLog;

/**
 * Created by rmiao on 3/20/2017.
 */
public class BillingModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(TransactionLog.class).to(DatabaseTransactionLog.class);
    }
}
