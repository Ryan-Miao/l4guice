package com.test.guice.bindings.logger.impl;

import com.test.guice.bindings.logger.DatabaseTransactionLog;

/**
 * Created by rmiao on 3/20/2017.
 */
public class MySqlDatabaseTrannsactionLog extends DatabaseTransactionLog {
    private String url;
    private Integer threadPoolSize;

    public MySqlDatabaseTrannsactionLog() {
    }

    public MySqlDatabaseTrannsactionLog(String url, Integer threadPoolSize) {
        this.url = url;
        this.threadPoolSize = threadPoolSize;
    }

    @Override
    public String getUrl() {
        return this.url;
    }

    @Override
    public Integer getThreadPoolSize() {
        return threadPoolSize;
    }

}
