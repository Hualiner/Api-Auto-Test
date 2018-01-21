package com.demo.common.runner;

import com.demo.common.GlobalVar;
import com.demo.common.utils.RetryUtils;
import com.jayway.restassured.response.Response;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RunWith(ListenerRunner.class)
public abstract class BaseRunner {

    protected final Logger logger = LoggerFactory.getLogger(getClass());
    protected Response response;

    @BeforeClass
    public static void beforeClass() {

    }

    @AfterClass
    public static void afterClass() {
    }

    // 失败重试
    @Rule
    public RetryUtils retryUtils = new RetryUtils(GlobalVar.RETRY_COUNTS);
}
