package org.tlh.springcloud.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;
import org.tlh.springcloud.entity.User;

//绑定服务提供者,同时也会创建同名的ribbon客户端
// 在配置文件中可以通过client.key的方式进行配置ribbon信息，同时注意ribbon的超时重试时间必须大于hystrix的熔断时间
@FeignClient(name="helloService")
public interface HelloService {

    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    String hello();

    @RequestMapping(value = "/hello1",method = RequestMethod.GET)
    String hello(@RequestParam("name")String name);//在feign客户端中必须通过value属性来显示指定参数的名称

    @RequestMapping(value = "/hello2",method = RequestMethod.GET)
    String hello(@RequestHeader("name")String name,@RequestHeader("age")int age);//在feign客户端中必须通过value属性来显示指定请求头名称

    @RequestMapping(value = "/hello3",method = RequestMethod.POST)
    String hello(@RequestBody User user);


}
