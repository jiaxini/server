package com.service.jiaxini.conf.shiro;

import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: orderman
 * @description: MyExceptionResover
 *          此类是为了解决shiro过滤器中setUnauthorizedUrl("")设置的路径不起作用
 *          原因是：anon，authcBasic，auchc，user是AuthenticationFilter，
 *                 perms，roles，ssl，rest，port才是属于AuthorizationFilter，
 *                 而定义的filter必须instanceof AuthorizationFilter这个方法setUnauthorizedUrl才生效
 *          自定义此类用来解决跳转的问题
 *
 *          //如果使用XML去配置的话就不会出现这样的问题
 * @author: ZengGuangfu
 * @create 2018-09-20 08:52
 */
public class MyExceptionResover implements HandlerExceptionResolver {
    @Nullable
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, @Nullable Object o, Exception exception) {
        System.out.println("shiro 拦截到异常");

        //如果异常属于授权异常
        if(exception instanceof UnauthorizedException){
            ModelAndView mv = new ModelAndView("index.html");
            return mv;
        }
        exception.printStackTrace();
        ModelAndView mv = new ModelAndView("error/404");
        mv.addObject("exception", exception.toString().replaceAll("\n", "<br/>"));
        return mv;
    }
}
