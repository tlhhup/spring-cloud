package org.tlh.springcloud.service;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheKey;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheRemove;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.tlh.springcloud.entity.User;

import java.util.List;

@Service("helloServce")
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

    //1.定义服务降级：Hystrix会进入fallBack尝试回退处理，该操作为"服务降级"
    /*
       以下情况不需要服务降级
    * 1.执行写操作的命令
    * 2.执行批处理或离线计算的命令
    * */
    @HystrixCommand(fallbackMethod = "helloFallBack")
    public String helloService(){
        return restTemplate.getForEntity("http://HELLOSERVICE/hello",String.class).getBody();
    }

    public String helloFallBack(){
        return "error";
    }

    //2.异常获取:通过在fallback方法中添加Throwable对象获取异常信息
    @HystrixCommand(fallbackMethod = "helloFallBack1")
    public User getUserById(int id){
        return restTemplate.getForEntity("http://HELLOSERVICE/hello/{1}",User.class,id).getBody();
    }

    public User helloFallBack1(int id, Throwable e){
        assert "".equals(e.getMessage());
        return null;
    }

    //3.请求缓存:通过添加@cacheResult注解处理缓存
    @CacheResult(cacheKeyMethod = "getCacheKey")
    @HystrixCommand
    public User findUserById(int id){
        return restTemplate.getForEntity("http://HELLOSERVICE/hello/{1}",User.class,id).getBody();
    }

    public String getCacheKey(int id){
        return Integer.toBinaryString(id);
    }

    @CacheRemove(commandKey = "findUserById",cacheKeyMethod = "getCacheKey")
    @HystrixCommand
    public void updateUserInfo(@CacheKey("id") User user){
        restTemplate.put("http://HELLOSERVICE/update",user);
    }

    //4.请求合并
    /*以下场景可以考虑使用请求合并
    *
    * 1.请求命令本身是一个高延迟的命令
    * 2.延迟时间窗内的并发量
    *
    * */
    @HystrixCollapser(batchMethod = "findAll")
    public User findUserInfoById(int id){
        return restTemplate.getForEntity("http://HELLOSERVICE/hello/{1}",User.class,id).getBody();
    }

    @HystrixCommand
    public List<User> findAll(List<Integer> ids){
        return restTemplate.getForEntity("http://HELLOSERVICE/hellos/{1}",List.class,ids).getBody();
    }

}
