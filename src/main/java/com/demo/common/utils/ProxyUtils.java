package com.demo.common.utils;

import com.demo.common.GlobalVar;
import com.demo.common.annotation.*;
import com.demo.common.entity.TestStep;
import com.demo.common.enums.HttpType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Wang Hualin
 * @Date: 2018/1/18
 */
public class ProxyUtils {
    private static Logger logger = LoggerFactory.getLogger(ProxyUtils.class);

    @SuppressWarnings("unchecked")
    public static <T> T create(Class<T> clazz) {

        // 获取接口上的HOST
        Annotation annotation = clazz.getAnnotation(SERVER.class);
        if (annotation == null) {
            throw new RuntimeException(String.format("接口类%s未配置@SERVER注解",
                    clazz.getName()));
        }

        String host;
        switch (clazz.getAnnotation(SERVER.class).value()){
            case GlobalVar.DOUBAN_MOVIE_SERVER:
                host = GlobalVar.DOUBAN_MOVIE_SERVER_URL;
                break;
            default:
                throw new RuntimeException(String.format("未查找到接口类%s配置的@HOST(%s)注解中的%s接口服务器地址",
                        clazz.getName(),
                        clazz.getAnnotation(SERVER.class).value(),
                        clazz.getAnnotation(SERVER.class).value()));
        }


        HttpUtils httpUtils = new HttpUtils(host);

        return (T) Proxy.newProxyInstance(clazz.getClassLoader(),
                new Class[]{clazz},
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                        // 方法上的注释及对应的值
                        Annotation[] annotations = method.getAnnotations();
                        if (annotations.length == 0) {
                            throw new RuntimeException(String.format("%s方法未配置请求类型注解，如@POST、@GET等",
                                    method.getName()));
                        }

                        HttpType httpType;
                        String path;
                        String description;

                        // 当前只需要解析一个注解
                        if (annotations[0] instanceof POST) {
                            httpType = HttpType.POST;
                            path = ((POST) annotations[0]).path();
                            description = ((POST) annotations[0]).description();
                        } else if (annotations[0] instanceof GET) {
                            httpType = HttpType.GET;
                            path = ((GET) annotations[0]).path();
                            description = ((GET) annotations[0]).description();
                        } else {
                            throw new RuntimeException(String.format("暂不支持%s方法配置的请求类型注解%s",
                                    method.getName(),
                                    annotations[0].annotationType()));
                        }

                        // 方法上参数对应的注解
                        Annotation[][] parameters = method.getParameterAnnotations();
                        Integer length = parameters.length;
                        TestStep testStep = new TestStep();
                        if (length != 0) {
                            Map<String, Object> map = new HashMap<>();
                            for (Integer i = 0; i < length; i++) {
                                // 参数注解类型
                                Annotation[] annos = parameters[i];
                                if (annos.length == 0) {
                                    throw new RuntimeException(String.format("方法%s中缺少参数注解，如@Param",
                                            method.getName()));
                                }

                                if (annos[0] instanceof Param) {
                                    map.put((((Param) annos[0]).value()), args[i]);
                                } else if (annos[0] instanceof PathVariable) {
                                    path = path.replaceFirst("\\{\\}", args[i].toString());
                                }
                                else {
                                    throw new RuntimeException(String.format("暂不支持方法%s中配置的参数注解%s",
                                            method.getName(),
                                            annos[0].annotationType()));
                                }
                            }
                            testStep.setParams(map);
                        }

                        testStep.setType(httpType);
                        testStep.setPath(path);

                        logger.info("[" + path + "]" + description);
                        return httpUtils.request(testStep);
                    }
                });
    }
}
