package com.demo.douban;

import com.demo.common.runner.BaseRunner;
import com.demo.common.utils.ProxyUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;

/**
 * MovieApi Tester.
 *
 * @author: Wang Hualin
 * @Date: 01/20/2018
 */
public class TestMovieApi extends BaseRunner {

    private MovieApi movieApi = ProxyUtils.create(MovieApi.class);

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     *
     */
    @Test
    public void testTop250() throws Exception {
        response = movieApi.top250(0, 1);

        logger.info(response.then().extract().path("subjects[0].title"));
        response.then().body("subjects[0].title", equalTo("肖申克的救赎"));
    }
}
