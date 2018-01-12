package org.tlh.springcloud.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

import javax.servlet.http.HttpServletRequest;

public class AccessFilter extends ZuulFilter {

    /**
     * 设置过滤阶段，
     *  pre：为在进行路由之前进行过滤
     *  route：在请求路由的时候调用
     *  post：在路由完成之后调用，及routing和error之后执行
     *  error：处理请求时发生错误时被调用
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 在同一类型的路由时，设置其执行的顺序
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 标识是否进行过滤
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();

        String accessToken = request.getParameter("accessToken");
        if(accessToken==null){
            currentContext.setSendZuulResponse(false);
            currentContext.setResponseStatusCode(401);
            return null;
        }
        return null;
    }
}
