package br.com.collegesmaster.config;

import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "br.com.collegesmaster.*.controller**", "br.com.collegesmaster.endpoint**" })
public class UiWebConfig implements WebMvcConfigurer {

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}
	
	@Bean
	public MessageSource messageSource() {
	    ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
	     
	    messageSource.setBasenames("classpath:ValidationMessages", "classpath:i18n/system-messages");
	    messageSource.setDefaultEncoding("UTF-8");
	    return messageSource;
	}
	
	@Bean
	public LocalValidatorFactoryBean getValidator() {
	    LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
	    bean.setValidationMessageSource(messageSource());
	    return bean;
	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry
			.addResourceHandler("/resources/**")
			.addResourceLocations("/resources/");
	}
	
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
	    ObjectMapper objectMapper = Jackson2ObjectMapperBuilder.json()
	    		.modules(new JavaTimeModule(), new Jdk8Module())
	    		.build()
	            .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
	    converters.add(new MappingJackson2HttpMessageConverter(objectMapper));
	}
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/collegesmaster/**")
			.allowedOrigins("http://localhost/4200/**").allowedMethods("GET", "POST","PUT", "DELETE");
	}
}
