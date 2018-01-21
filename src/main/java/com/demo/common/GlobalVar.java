package com.demo.common;

import com.demo.common.utils.ListenerUtils;

public class GlobalVar {

    public static final String DOUBAN_MOVIE_SERVER = "DOUBAN_MOVIE_SERVER";
    public static String DOUBAN_MOVIE_SERVER_URL = "http://api.douban.com/v2/movie";

    // 失败重试，等于2，则失败重试1次，共执行2次
    public final static Integer RETRY_COUNTS = 2;

    public static String REPORT_PATH = "target/reports/";

    public final static ListenerUtils listenerUtils = new ListenerUtils();
}
