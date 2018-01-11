package org.tlh.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

<<<<<<< HEAD
@EnableHystrix
=======
/*@EnableHystrix
>>>>>>> 16a7f9e8b4577556016d1969b8a4c0edc1bf5458
@EnableDiscoveryClient
@SpringBootApplication*/
@SpringCloudApplication
public class Application {

    @Bean
    @LoadBalanced//开启客户端的负载均衡
    RestTemplate restTemplate(){
        return new RestTemplate();
    }


    public static void main(String[] args){
        SpringApplication.run(Application.class,args);
    }

}
