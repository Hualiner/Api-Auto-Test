package com.demo.common.utils;

import com.demo.common.entity.TestStep;
import com.demo.common.enums.HttpType;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.config.RestAssuredConfig;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

import static com.demo.common.GlobalVar.COOKIES;
import static com.demo.common.GlobalVar.HEADERS;
import static com.demo.common.GlobalVar.listenerUtils;
import static com.jayway.restassured.RestAssured.config;
import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.config.JsonConfig.jsonConfig;
import static com.jayway.restassured.path.json.config.JsonPathConfig.NumberReturnType.DOUBLE;


public class HttpUtils {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private RestAssuredConfig restAssuredConfig;
    private Response response;
    private String baseURL;


    /**
     * 构造方法
     */
    HttpUtils(String url) {
        baseURL = url;

        // 根据需要进行设置
        restAssuredConfig = config()
                .jsonConfig(jsonConfig().numberReturnType(DOUBLE));
    }


    /**
     * 获取本次请求的URL，携带参数
     *
     * @param path
     * @param params
     */
    private String getRequestInfo(String path, Map<String, Object> params) {
        StringBuilder stringBuilder = new StringBuilder();

        for (String key : params.keySet()) {
            stringBuilder.append(key).append("=").append(params.get(key)).append("&");
        }

        if (stringBuilder.length() >= 1 && stringBuilder.toString().endsWith("&")) {
            stringBuilder = new StringBuilder(stringBuilder.substring(0, stringBuilder.length() - 1));
        }

        return getRequestInfo(path) + "?" + stringBuilder;
    }

    /**
     * 获取本次请求的URL，不携带参数
     *
     * @param path
     */
    private String getRequestInfo(String path) {
        return RestAssured.baseURI + path;
    }

    /**
     * 获取本次请求的响应信息
     *
     * @param response
     */
    private String getResponseInfo(Response response) {

        // TODO - 此处容易抛异常
        if (response.contentType().contains("json")) {
            return "[" + response.statusCode() + "]" + response.jsonPath().get();
        } else {
            return "[" + response.statusCode() + "]" + response.htmlPath().get();
        }
    }

    /**
     * 装载此次请求配置
     *
     * @param path
     */
    private RequestSpecification getRequestSpecification(String path) {
        return given().headers(HEADERS).cookies(COOKIES).config(restAssuredConfig).basePath(path);
    }

    /**
     * 携带参数的请求
     *
     * @param httpType
     * @param path
     * @param params
     */
    private Response request(HttpType httpType, String path, Map<String, Object> params) {
        logger.info("[" + httpType.getValue() + "]" + getRequestInfo(path, params));

        switch (httpType) {
            case GET:
                response = getRequestSpecification(path).params(params).get();
                break;
            case POST:
                response = getRequestSpecification(path).params(params).post();
                break;
            default:
                throw new RuntimeException(String.format("暂不支持%s请求类型", httpType));
        }

        return response;
    }

    /**
     * 不携带参数的请求
     *
     * @param httpType
     * @param path
     */
    private Response request(HttpType httpType, String path) {
        logger.info("[" + httpType.getValue() + "]" + getRequestInfo(path));

        switch (httpType) {
            case GET:
                response = getRequestSpecification(path).get();
                break;
            case POST:
                response = getRequestSpecification(path).post();
                break;
            default:
                throw new RuntimeException(String.format("暂不支持%s请求类型", httpType));
        }

        return response;
    }

    /**
     * 请求
     *
     * @param testStep
     */
    public Response request(TestStep testStep) {
        RestAssured.baseURI = baseURL;

        if (!testStep.getParams().isEmpty()) {
            response = request(testStep.getType(), testStep.getPath(), testStep.getParams());
        } else {
            response = request(testStep.getType(), testStep.getPath());
        }

        logger.info(getResponseInfo(response));

        // TODO - 此处容易抛异常
        if (response.contentType().contains("json")) {
            listenerUtils.testCaseResult.setDescription(response.jsonPath().get().toString());
        } else {
            listenerUtils.testCaseResult.setDescription(response.htmlPath().get().toString());
        }

        return response;
    }
}

