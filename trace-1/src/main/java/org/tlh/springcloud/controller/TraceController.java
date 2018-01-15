package org.tlh.springcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class TraceController {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/trace-1")
    public String trace(){
        return this.restTemplate.getForObject("http://trace-2/trace-2",String.class);
    }

}
