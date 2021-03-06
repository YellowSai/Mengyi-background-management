package com.jianzheng.server.configure;

import com.jianzheng.server.config.AppConfig;
import com.jianzheng.server.web.AdminServiceInterceptor;
import com.jianzheng.server.web.WebServiceInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * web配置<br>
 * 用于注入拦截器
 *
 * @author tanghuang 2020年02月25日
 */
@Configuration
public class WebConfigurerAdapter extends WebMvcConfigurationSupport {

    @Autowired
    private AppConfig appConfig;

    @Bean
    AdminServiceInterceptor adminServiceInterceptor() {
        return new AdminServiceInterceptor();
    }

    @Bean
    WebServiceInterceptor webServiceInterceptor() {
        return new WebServiceInterceptor();
    }

    /**
     * 添加拦截器
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //管理后台拦截
        registry.addInterceptor(adminServiceInterceptor()).addPathPatterns("/admapi/**").excludePathPatterns("/admapi/login");
        //小程序接口拦截
        registry.addInterceptor(webServiceInterceptor()).addPathPatterns("/api/**").excludePathPatterns("/api/user/code2session", "/api/user/login", "/api/airport/list","/api/channel/apply");
    }


    /**
     * 需要重新指定静态资源，解决swagger与WebMvcConfigurationSupport冲突导致404的问题
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
        // 测试时上传路径转发
        registry.addResourceHandler("/upload/**").addResourceLocations("file:" + appConfig.getUploadBasePath());
        super.addResourceHandlers(registry);
    }

}
