package com.wisdom.filter;

import com.wisdom.entity.UsersEntity;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * 过滤器
 * urlPatterns = "/*"：拦截所有Controller地址，initParams:设置不需要过滤的地址，unCheckedUrls：不需要过滤的跳转方法，staticUrls：静态资源的后缀名
 */
/*@Slf4j//如果不想每次都写private  final Logger logger = LoggerFactory.getLogger(当前类名.class); 可以用注解@Slf4j; 1.使用idea首先需要安装Lombok插件;
@WebFilter(filterName = "LoginFilter", urlPatterns = "/*", initParams = {@WebInitParam(name = "unCheckedUrls", value = "/login/*,/,/login/wisdom,/login/doLogin"),
        @WebInitParam(name = "staticUrls", value = "/static/**,.css,.js,.jpg,.png,.ico,.gif,.map,.ftl,.html,.jsp")})
public class LoginFilter implements Filter {
    private String unCheckedUrls;
    private String staticUrls;
    private String[] urls;

    @Override
    public void init(FilterConfig filterConfig) {
        log.info("============初始化==============");
        unCheckedUrls = filterConfig.getInitParameter("unCheckedUrls");
        staticUrls = filterConfig.getInitParameter("staticUrls");
    }

    //销毁方法
    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        boolean needChecking = true;
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        //  获得页面请求的类型，如果 type 为 XMLHttpRequest 则为 Ajax 请求。
        String type = httpServletRequest.getHeader("X-Requested-With") == null ? "" : httpServletRequest.getHeader("X-Requested-With");
        //  获得请求地址
        String servletPath = httpServletRequest.getRequestURI();
        log.info("servletPath ========== " + servletPath);

        if (null != unCheckedUrls && unCheckedUrls.length() > 0) {
            urls = unCheckedUrls.split(String.valueOf(","));
            for (String url : urls) {
                if (servletPath.equals(url)) {
                    needChecking = false;
                    break;
                }
            }
        }

        if (null !=staticUrls && staticUrls.length()>0) {
            urls = staticUrls.split(String.valueOf(","));
            for (String url : urls) {
                if (servletPath.endsWith(url)) {
                    needChecking = false;
                    break;
                }
            }
        }

        if (!needChecking) { //不需要过滤直接传给下一个过滤器
            //放行，传给下一个过滤器
            chain.doFilter(httpServletRequest, httpServletResponse);
        } else {
//             使用session获取实体类对象
            UsersEntity currentAdmin = (UsersEntity) httpServletRequest.getSession().getAttribute("loginUser");

            if (currentAdmin != null) {
                log.info("username==============>>" + currentAdmin.getUsername());
                //放行，传给下一个过滤器
                chain.doFilter(httpServletRequest, httpServletResponse);
            } else {
                //判断是否是ajax请求
                if (type != null && "XMLHttpRequest".equals(type)) {
                    httpServletResponse.getWriter().write("/?msg=" + URLEncoder.encode("请先登录", "UTF-8"));
                } else {
                    //重定向到登录页(需要在static文件夹下建立此html文件)
                    httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/login/wisdom");
                }
                return;
            }
        }
    }
}*/

