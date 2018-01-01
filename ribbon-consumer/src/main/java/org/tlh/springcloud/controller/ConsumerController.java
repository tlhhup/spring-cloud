package org.tlh.springcloud.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.tlh.springcloud.service.HelloService;

import javax.annotation.Resource;

@RestController
public class ConsumerController {

    @Resource
    private HelloService helloService;

    @RequestMapping(value = "/ribbon-consomer",method = RequestMethod.GET)
    public String helloConsumer(){
        return this.helloService.helloService();
    }

}
