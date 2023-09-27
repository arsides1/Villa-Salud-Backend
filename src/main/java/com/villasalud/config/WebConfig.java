package com.villasalud.config;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.support.OpenSessionInViewInterceptor;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
/*
* En este ejemplo, se está agregando el interceptor OpenSessionInViewInterceptor al
* registro de interceptores de la aplicación. El interceptor requiere una instancia de
* SessionFactory para poder abrir la sesión de Hibernate, por lo que estamos inyectando la instancia a través de Autowired.
* */
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        OpenSessionInViewInterceptor osivi = new OpenSessionInViewInterceptor();
        osivi.setSessionFactory(sessionFactory);
        registry.addWebRequestInterceptor(osivi);
    }
}