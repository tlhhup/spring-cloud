package org.tlh.springcloud.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TraceController {

    private static Logger logger=Logger.getLogger(TraceController.class);

    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("/trace-2")
    public String trace(){
        logger.info("serviceId:"+discoveryClient.getLocalServiceInstance().getServiceId());
        return "trace";
    }

}
