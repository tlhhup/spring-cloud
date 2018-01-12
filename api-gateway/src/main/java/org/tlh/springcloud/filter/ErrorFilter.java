package org.tlh.springcloud.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;

/** 异常处理-->最终都是由SendErrorFilter统一处理发送给用户
 * 1.通过定义error类型的filter集中处理pre、post、route阶段的异常信息
 * 2.通过在filter总通过try-catch的语法处理异常，在异常中通过对requestcontext设置error.status_code、error.message、error.exception属性信息即可
 *
 * 禁用过滤器：
 *      通过在配置文件中添加格式如：zuul.FilterSimpleName.filterType.disable=true 的属性来实现
 */
@Component
public class ErrorFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return "error";//当三个阶段任何一个出现异常都会进入error过滤器
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext currentContext = RequestContext.getCurrentContext();
        Throwable throwable = currentContext.getThrowable();
        //设置异常信息，最后也是交个post 阶段的filter(SendErrorFilter)进行处理
        currentContext.set("error.status_code", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);//设置状态码
        currentContext.set("error.message", throwable.getMessage());
        currentContext.set("error.exception", throwable.getCause());
        return null;
    }
}
