package org.tlh.springcloud.service.impl;

import org.springframework.stereotype.Component;
import org.tlh.springcloud.entity.User;
import org.tlh.springcloud.service.HelloService;

/**
 * 通过实现接口类，并通过FeignClient注解的fallback属性来配置服务降级类
 */
@Component
public class HelloServiceImpl implements HelloService {
    @Override
    public String hello() {
        return null;
    }

    @Override
    public String hello(String name) {
        return null;
    }

    @Override
    public String hello(String name, int age) {
        return null;
    }

    @Override
    public String hello(User user) {
        return null;
    }
}
