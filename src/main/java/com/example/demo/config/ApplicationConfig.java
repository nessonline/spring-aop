package com.example.demo.config;

import com.example.demo.aop.UserServiceAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

@Import({
        SwaggerConfig.class
})
@ComponentScan(basePackages = {"com.example.demo"})
@EnableAspectJAutoProxy
public class ApplicationConfig {

    /*@Bean("userServiceAspect")
    UserServiceAspect userServiceAspect() {
        return new UserServiceAspect();
    }*/
}
