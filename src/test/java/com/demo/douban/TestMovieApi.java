package com.demo.douban;

import com.demo.common.runner.BaseRunner;
import com.demo.common.utils.ProxyUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.demo.common.GlobalVar.HEADERS;
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
        HEADERS.put("code", "123456");
        HEADERS.put("token", "2ae3e7aeaf4642a4b6b1914d857b235d");

        response = movieApi.top250(0, 1);
        response.then().body("subjects[0].title", equalTo("肖申克的救赎"));
    }

    @Test
    public void testCelebrity() throws Exception {
        response = movieApi.celebrity(1031931);
    }
}
