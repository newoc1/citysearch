package grok.citysearch;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import grok.citysearch.loaders.ApplicationUserLoader;
import grok.citysearch.loaders.CityLoader;
import grok.citysearch.loaders.CommodityLoader;
import grok.citysearch.loaders.UserLoader;

@Component
public class TestDataPopulator {

	private ApplicationUserLoader applicationUserLoader;
	private CityLoader cityLoader;
	private CommodityLoader commodityLoader;
	private UserLoader userLoader;
	
	@Autowired
	public TestDataPopulator(ApplicationUserLoader applicationUserLoader, CityLoader cityLoader, CommodityLoader commodityLoader, UserLoader userLoader) {
		this.applicationUserLoader = applicationUserLoader;
		this.cityLoader = cityLoader;
		this.commodityLoader = commodityLoader;
		this.userLoader = userLoader;
	}
	/**
	 * Populate data in the specific order required by the JPA provider. Some entities
	 * depend on other entities already existing in order to be constructed in a valid manner.
	 */
	@PostConstruct
	private void populate(){
		applicationUserLoader.populate();
		commodityLoader.populate();
		userLoader.populate();
		cityLoader.populate();
	}
}
