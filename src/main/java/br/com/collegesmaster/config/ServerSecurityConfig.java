package br.com.collegesmaster.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.collegesmaster.security.model.service.UserService;

@Configuration
@EnableWebSecurity
@ComponentScan(basePackages= {"br.com.collegesmaster.*.model.service", "br.com.collegesmaster.config", "br.com.collegesmaster.*.facade"})
@EnableJpaRepositories("br.com.collegesmaster.*.model.repository")
@EntityScan("br.com.collegesmaster.*.model.entity")
public class ServerSecurityConfig extends WebSecurityConfigurerAdapter {
	
	private static final Logger logger = LogManager.getLogger(ServerSecurityConfig.class);
	
	@Autowired
	private UserService userService;
	
	@Autowired
	public void setApplicationContext(ApplicationContext context) {
	    super.setApplicationContext(context);
	    AuthenticationManagerBuilder globalAuthBuilder = context
	            .getBean(AuthenticationManagerBuilder.class);
	    try {
			globalAuthBuilder.userDetailsService(userService);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	
	@Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userService);
		authProvider.setPasswordEncoder(encoder());
		return authProvider;
	}

	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder(11);
	}
}
