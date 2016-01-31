package grok.citysearch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.repository.init.Jackson2RepositoryPopulatorFactoryBean;

import grok.citysearch.loaders.CityLoader;

@SpringBootApplication
public class CitySearchApplication {
	public static void main(String[] args) {
		SpringApplication.run(CitySearchApplication.class, args);
	}

	@Autowired
	private CityLoader cityLoader;
	
	// TODO: use this once data population is figured out more.
	// @Bean
	public Jackson2RepositoryPopulatorFactoryBean commodityLoader() {
		Resource sourceData = new ClassPathResource("test-data.json");
		Jackson2RepositoryPopulatorFactoryBean factory = new Jackson2RepositoryPopulatorFactoryBean();
		factory.setResources(new Resource[] { sourceData });
		return factory;
	}

}
