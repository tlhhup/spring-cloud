package org.tlh.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.filters.discovery.PatternServiceRouteMapper;
import org.springframework.context.annotation.Bean;
import org.tlh.springcloud.filter.AccessFilter;

@EnableZuulProxy
@SpringCloudApplication
public class Application {

    public static void main(String[] args){
        SpringApplication.run(Application.class,args);
    }

    @Bean
    AccessFilter accessFilter(){
        return new AccessFilter();
    }

    /**
     * 自定义映射规则，如果存在服务实例及版本问题是，其默认的映射规则为serviceId-version
     */
    @Bean
    public PatternServiceRouteMapper patternServiceRouteMapper(){
        return new PatternServiceRouteMapper("(?<name>^.+)-(?<version>v.+$)","$(version)/$(name)");
    }

    /**
     * 路由的匹配规则：如出现以下规则
     * zuul.routes.api-a.path=/api-a/**
     * zuul.routes.api-a.path=/api-a/ext/**
     *  此时匹配的顺序是更具文件中配置的顺序决定的，而properties文件无法保证加载的顺序所以推荐使用yml文件处理
     */

    /**
     * cookie与头信息， 默认情况下zuul会过滤掉http请求中的敏感信息,包括cookie、set-cookie和authorization三个属性
     * 1.全局设置
     *   zuul.sensitive-headers=     覆盖掉所有的属性
     * 2.通过指定路由的参数来设置   ---->进队web应用开启敏感信息传递
     *   zuul.routes.api-a.customSensitiveHeaders=true  开启自定义敏感头
         zuul.routes.api-a.sensitiveHeaders=
     */

}
