package com.hybird.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import java.util.Locale;

@Configuration
public class ResourceConfig {
    @Bean(name = "messageSource")
    public MessageSource getMessageSource()
    {
        ReloadableResourceBundleMessageSource ms =
                new ReloadableResourceBundleMessageSource();

        ms.setBasenames("classpath:messages/validation");
        ms.setDefaultEncoding("UTF-8");

        return ms;
    }
    @Bean
    public MessageSourceAccessor getMessageSourceAccessor(final MessageSource messageSource) {
        return new MessageSourceAccessor(messageSource, Locale.US);
    }
}
