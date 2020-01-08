package hu.molnaran.todobackend.config;

import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

import java.time.format.DateTimeFormatter;

@Configuration
public class AppConfig {

    @Value("${spring.jackson.date-format}")
    private String dateFormat;

    @Value("${spring.jackson.datetime-format}")
    private String dateTimeFormat;

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("errormessages");
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setCacheSeconds(10); //reload messages every 10 seconds
        messageSource.setUseCodeAsDefaultMessage(true);
        return messageSource;
    }


    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jsonCustomizer() {
        return builder -> {
            builder.simpleDateFormat(dateTimeFormat);
            builder.serializers(new LocalDateSerializer(DateTimeFormatter.ofPattern(dateFormat)));
            builder.serializers(new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(dateTimeFormat)));
        };
    }
}
