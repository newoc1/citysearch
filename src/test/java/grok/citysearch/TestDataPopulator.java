package grok.citysearch;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import grok.citysearch.loaders.ApplicationUserLoader;
import grok.citysearch.loaders.CityLoader;
import grok.citysearch.loaders.CommodityLoader;

@Component
public class TestDataPopulator {

	@Autowired
	private ApplicationUserLoader applicationUserLoader;
	@Autowired
	private CityLoader cityLoader;
	@Autowired
	private CommodityLoader commodityLoader;
	
	/**
	 * Populate data in the specific order required by the JPA provider. Some entities
	 * depend on other entities already existing in order to be constructed in a valid manner.
	 */
	@PostConstruct
	private void populate(){
		applicationUserLoader.populate();
		commodityLoader.populate();
		cityLoader.populate();
	}
}
