package grok.citysearch.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import grok.citysearch.city.City;
import grok.citysearch.city.CityService;
import grok.citysearch.commodity.Commodity;
import grok.citysearch.common.mediator.CommodityExchange;


@RestController
public class CityController {

	private CityService cityService;
	private CommodityExchange commodityExchange;
	
	@Autowired
	public CityController( CityService cityService, CommodityExchange commodityExchange) {
		this.cityService = cityService;
		this.commodityExchange = commodityExchange;
	}

	@RequestMapping("/cities")
	public Page<City> findCities(@RequestParam(required = false) String name, Pageable pageable) {
		return cityService.findCities(name, pageable);
	}

	@RequestMapping("cities/{cityId}")
	public City get(@PathVariable String cityId) {
		System.out.println("Retrieving city");
		return cityService.get(cityId);
	}

	@RequestMapping(path = "cities/{cityId}/commodities", method = RequestMethod.POST)
	public void supplyCommodity(@PathVariable String cityId, @RequestBody Commodity commodity) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName(); // get the request user's username
		commodityExchange.supplyCommodity(cityId, username, commodity.getName());
	}
}
