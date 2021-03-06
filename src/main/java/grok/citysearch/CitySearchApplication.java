package grok.citysearch;

import java.util.Random;

import javax.annotation.Resource;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;

import org.springframework.data.repository.init.Jackson2RepositoryPopulatorFactoryBean;
import org.springframework.data.solr.core.SolrOperations;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.repository.config.EnableSolrRepositories;
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
@EnableSolrRepositories("grok.citysearch")
public class CitySearchApplication extends WebMvcConfigurerAdapter {
	static final String SOLR_HOST = "solr.host";

	@Resource
	private Environment environment;

	public static void main(String[] args) {
		SpringApplication.run(TestCitySearchApplication.class, args);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public UserDetailsService userDetailsService() {
		return new CustomUserDetailsService();
	}

	@Bean
	public Random getRandom() {
		return new Random();
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("index/index");
		registry.addViewController("/login").setViewName("login/login");
		registry.addViewController("/access").setViewName("/access/access");
	}

	@Bean
	public AuthenticationSecurity authenticationSecurity() {
		return new AuthenticationSecurity();
	}

	@Bean
	public ApplicationSecurity applicationSecurity() {
		return new ApplicationSecurity();
	}

	/**
	 * Defines the client connection to a solr server.
	 * @return
	 */
	@Bean
	public SolrServer solrServer() {
		String solrHost = environment.getRequiredProperty(SOLR_HOST);
		SolrServer solrServer = new HttpSolrServer(solrHost);
		return solrServer;
	}

	/**
	 * Bean that allows more advanced queries. For example using {@link Facet} for solr queries.
	 * @return
	 */
	@Bean
	public SolrOperations solrTemplate() {
		return new SolrTemplate(solrServer());
	}

}
