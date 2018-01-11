package org.tlh.springcloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service("helloService")
public class HelloService {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "fallBack")
    public String hello(){
        return restTemplate.getForEntity("http://HELLOSERVICE/hello",String.class).getBody();
    }

    public String fallBack(){
        return "fail";
    }

}
