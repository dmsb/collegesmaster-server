package br.com.collegesmaster.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "br.com.collegesmaster.*.model.repository")
@EntityScan("br.com.collegesmaster.*.model.entity")
public class ApplicationBoot extends SpringBootServletInitializer {
	
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ApplicationBoot.class);
    }
	
    public static void main(String[] args) {
        SpringApplication.run(ApplicationBoot.class, args);
    }
}
