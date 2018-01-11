package org.tlh.springcloud.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.tlh.springcloud.entity.User;
import org.tlh.springcloud.service.HelloService;

import javax.annotation.Resource;

@RestController
public class FeignConsumer {

    @Resource
    private HelloService helloService;

    @RequestMapping(value = "/feign-consumer",method = RequestMethod.GET)
    String hello(){
        return this.helloService.hello();
    }

    @RequestMapping(value = "/feign-consumer-2",method = RequestMethod.GET)
    String hello2(){
        StringBuilder builder=new StringBuilder();
        builder.append(helloService.hello()).append("\n");
        builder.append(helloService.hello("张三")).append("\n");
        builder.append(helloService.hello("李四",10)).append("\n");
        builder.append(helloService.hello(new User("王五",20))).append("\n");
        return builder.toString();
    }

}
