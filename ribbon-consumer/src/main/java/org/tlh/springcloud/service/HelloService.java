package org.tlh.springcloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service("helloServce")
public class HelloService {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "helloFallBack")
    public String helloService(){
        return restTemplate.getForEntity("http://HELLOSERVICE/hello",String.class).getBody();
    }

    public String hellFallBack(){
        return "error";
    }

}
