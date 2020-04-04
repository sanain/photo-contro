package com.sanain.photo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 配置类
 * @Author sinain
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    /**
     * 支持跨域
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("PUT", "DELETE","GET","POST")
                .allowedHeaders("*")
                .exposedHeaders("access-control-allow-headers",
                "access-control-allow-methods",
                "access-control-allow-origin",
                "access-control-max-age",
                        "X-Frame-Options")
                .allowCredentials(true);
    }




    /**
     * 返回一个验证登录的拦截器
     * @return
     */
    @Bean
    public LoginInterceptor getLoginInterceptor(){
        return new LoginInterceptor();
    }

//    @Bean
//    public FilterConfig getFilterConfig(){
//        return new FilterConfig();
//    }

    /**
     * 添加拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> list = new ArrayList<String>();
        list.add("/user/login");
        list.add("/user/getLoginCode");
        list.add("/user/register");
        list.add("/user/getRegisterCode");

//        registry.addInterceptor(getFilterConfig()).addPathPatterns("/**");
        //excludePathPatterns 排查那些路径不被拦截
        registry.addInterceptor(getLoginInterceptor()).excludePathPatterns(list);

//        super.addInterceptors(registry);
    }
}
