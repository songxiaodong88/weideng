package com.wisdom.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfigurer implements WebMvcConfigurer {
//
    @Autowired
    private LoginInterceptor loginInterceptor;
////
////    // 这个方法是用来配置静态资源的，比如html，js，css，等等
 /*   @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("http://129.28.156.225/");
    }*/
//
//    // 这个方法用来注册拦截器，我们自己写好的拦截器需要通过这里添加注册才能生效
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        System.out.println("=====>拦截器Start");
        // addPathPatterns("/**") 表示拦截所有的请求，
        // excludePathPatterns("/login", "/register") 表示除了登陆与注册之外，因为登陆注册不需要登陆也可以访问
//        registry.addInterceptor(loginInterceptor).addPathPatterns("/**").excludePathPatterns("/wisdom","/login/wisdom", "/","/login/*","/static/*","/login/logout","/login/toIndex","/test66666666666"/*, "/css/**", "/docs/**", "/fonts/**", "/img/**", "/js/**", "/favicon.ico", "/test", "/doAuth"*/);
        InterceptorRegistration registration = registry.addInterceptor(loginInterceptor);
        //  表示拦截所有的请求，
        registration.addPathPatterns("/**");
        //  表示除了登陆与注册之外，因为登陆注册不需要登陆也可以访问,添加不需要拦截的地址
        registration.excludePathPatterns("/");
        registration.excludePathPatterns("/wisdom");
        registration.excludePathPatterns("/login/wisdom");
        registration.excludePathPatterns("/login/doLogin");
        registration.excludePathPatterns("http://129.28.156.225/**","/static/**","/css/**", "/fonts/**", "/img/**", "/images/**","/js/**", "/favicon.ico", "/test", "/doAuth");
    }


}
