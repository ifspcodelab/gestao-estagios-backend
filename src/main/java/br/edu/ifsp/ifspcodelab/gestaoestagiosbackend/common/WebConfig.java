package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.converters.StringToEntityStatusEnumConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringToEntityStatusEnumConverter());
    }
}
