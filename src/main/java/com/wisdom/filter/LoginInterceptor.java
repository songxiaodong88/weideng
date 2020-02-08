package com.wisdom.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/*@Component
public class LoginInterceptor implements HandlerInterceptor {

    //    * logger
    private static final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse response, Object o) throws Exception {
        logger.info("---------------------开始进入请求地址拦截----------------------------");
        HttpSession session = httpServletRequest.getSession();
        //   HttpSession session = HttpContextUtils.getHttpServletRequest().getSession();
        Object user = session.getAttribute("loginUser");
        if (user == null) {
            System.out.println("=====================" + httpServletRequest.getRequestURI());
            try {
                response.sendRedirect("/login/wisdom");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }

}*/
