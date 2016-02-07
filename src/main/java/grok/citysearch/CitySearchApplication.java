package grok.citysearch;

import java.util.Random;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.repository.init.Jackson2RepositoryPopulatorFactoryBean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import grok.citysearch.loaders.CityLoader;
import grok.citysearch.security.ApplicationSecurity;
import grok.citysearch.security.AuthenticationSecurity;
import grok.citysearch.security.CustomUserDetailsService;

@SpringBootApplication
public class CitySearchApplication extends WebMvcConfigurerAdapter {
	public static void main(String[] args) {
		SpringApplication.run(CitySearchApplication.class, args);
	}

	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
	@Bean
	public UserDetailsService userDetailsService(){
		return new CustomUserDetailsService();
	}
	
	@Bean
	public CityLoader cityLoader(){
		return new CityLoader(100,3,10000000L);
	}
	
	// TODO: use this once data population is figured out more.
	// @Bean
	public Jackson2RepositoryPopulatorFactoryBean commodityLoader() {
		Resource sourceData = new ClassPathResource("test-data.json");
		Jackson2RepositoryPopulatorFactoryBean factory = new Jackson2RepositoryPopulatorFactoryBean();
		factory.setResources(new Resource[] { sourceData });
		return factory;
	}
	
	@Bean
	public Random getRandom(){
		return new Random();
	}
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("index/index");
		registry.addViewController("/login").setViewName("login/login");
		registry.addViewController("/access").setViewName("/access/access");
	}

	@Bean
	public AuthenticationSecurity authenticationSecurity(){
		return new AuthenticationSecurity();
	}
	@Bean
	public ApplicationSecurity applicationSecurity() {
		return new ApplicationSecurity();
	}


}
