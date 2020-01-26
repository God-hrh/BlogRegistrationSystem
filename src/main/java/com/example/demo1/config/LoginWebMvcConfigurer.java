package com.example.demo1.config;

        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.context.annotation.Configuration;
        import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
        import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class LoginWebMvcConfigurer implements WebMvcConfigurer {
    @Autowired
    private AuthHanderInterceptor authHanderInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authHanderInterceptor).addPathPatterns("/**");
    }
}
