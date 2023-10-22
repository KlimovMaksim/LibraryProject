package ru.klimov.springmvc.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class MySpringMvcDispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return null;
    }

    @Override // указываем класс со Spring конфигурацией
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{SpringConfig.class};
    }

    @Override // все запросы от пользователя посылаем на dispatcher servlet
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
