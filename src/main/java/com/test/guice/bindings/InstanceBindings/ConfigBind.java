package com.test.guice.bindings.InstanceBindings;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Test bind string or integer to variable.
 *
 * Created by rmiao on 3/23/2017.
 */
public class ConfigBind {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigBind.class);


    @Inject
    @Named("jdbc.url")
    private String jdbcUrl;

    @Inject
    @Named("jdbc.timeout")
    private Integer timeout;

    public ConfigBind() {
    }

//    @Inject
//    public ConfigBind(@Named("jdbc.url")String jdbcUrl,
//                      @Named("jdbc.timeout")Integer timeout) {
//        this.jdbcUrl = jdbcUrl;
//        this.timeout = timeout;
//    }

    public boolean connect(){
        LOGGER.info("connect stat:");
        LOGGER.info("The  url is: {}, and time out is: {}", jdbcUrl, timeout);
        if(isBlank(jdbcUrl) || isBlank(timeout)){
            return false;
        }

        return true;
    }

    private boolean isBlank(Integer timeout) {
        return timeout == null;
    }
    private boolean isBlank(String jdbcUrl) {
        return StringUtils.isBlank(jdbcUrl);
    }

}
