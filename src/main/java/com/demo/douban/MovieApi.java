package com.demo.douban;

import com.demo.common.GlobalVar;
import com.demo.common.annotation.GET;
import com.demo.common.annotation.Param;
import com.demo.common.annotation.PathVariable;
import com.demo.common.annotation.SERVER;
import com.jayway.restassured.response.Response;

/**
 * @Author: Wang Hualin
 * @Date: 2018/1/18
 */
@SERVER(GlobalVar.DOUBAN_MOVIE_SERVER)
public interface MovieApi {

    /**
     * @param start
     */
    @GET(path = "/top250", description = "豆瓣电影 top 250")
    Response top250(@Param("start") Integer start,
                    @Param("count") Integer count);

    @GET(path = " /celebrity/{}", description = "影人条目信息")
    Response celebrity(@PathVariable Integer id);
}
