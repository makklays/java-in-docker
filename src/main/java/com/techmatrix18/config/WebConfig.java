package com.techmatrix18.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * Configuration class for configuring a web.
 *
 * @author Alexander Kuziv
 * @since 08-07-2025
 * @version 0.0.1
 */

@Configuration
public class WebConfig implements WebMvcConfigurer {

    // Регистрируем конвертер (ProtobufHttpMessageConverter) из protobuf в Content type (без конвертера нету Content type - null)
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new ProtobufHttpMessageConverter());
    }
}

