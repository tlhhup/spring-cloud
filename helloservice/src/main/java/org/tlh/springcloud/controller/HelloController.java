package org.tlh.springcloud.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;
import org.tlh.springcloud.entity.User;


@RestController
public class HelloController {

    private final Logger logger=Logger.getLogger(getClass());

    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    String hello(){
        ServiceInstance instance = discoveryClient.getLocalServiceInstance();

        logger.info("/hello,host:"+instance.getHost()+",service_id:"+instance.getServiceId());
        return "hello,world";
    }


    @RequestMapping(value = "/hello1",method = RequestMethod.GET)
    String hello(@RequestParam String name){
        return "Hello "+name;
    }

    @RequestMapping(value = "/hello2",method = RequestMethod.GET)
    User hello(@RequestHeader String name, @RequestHeader int age){
        return new User(name,age);
    }

    @RequestMapping(value = "/hello3",method = RequestMethod.POST)
    String hello(@RequestBody User user){
        return "Hello "+user.getName()+", "+user.getAge();
    }

}
