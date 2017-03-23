package com.test.guice.bindings.service.impl;

import com.test.guice.bindings.service.City;
import com.test.guice.bindings.service.IPersonSay;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by rmiao on 3/20/2017.
 */
public class ShenZhenSay implements IPersonSay{
    public static final Logger LOGGER = LoggerFactory.getLogger(ShenZhenSay.class);
    @Override
    public City say() {
        LOGGER.info("I'm from ShenZhen");
        return City.SHEN_ZHEN;
    }
}
