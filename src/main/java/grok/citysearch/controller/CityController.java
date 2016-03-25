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
import grok.citysearch.user.UserService;


@RestController
public class CityController {

	private CityService cityService;
	private CommodityExchange commodityExchange;
	private UserService userService;
	
	@Autowired
	public CityController( CityService cityService, CommodityExchange commodityExchange, UserService userService) {
		this.cityService = cityService;
		this.commodityExchange = commodityExchange;
		this.userService = userService;
	}

	@RequestMapping("/cities")
	public Page<City> findCities(@RequestParam(required = false) String name, Pageable pageable) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		Integer userRank = userService.getUserRank(username);
		return cityService.findCities(name, userRank, pageable);
	}

	@RequestMapping("cities/{cityId}")
	public City get(@PathVariable String cityId) {
		System.out.println("Retrieving city");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		Integer userRank = userService.getUserRank(username);
		return cityService.get(cityId, userRank);
	}

	@RequestMapping(path = "cities/{cityId}/commodities", method = RequestMethod.POST)
	public void supplyCommodity(@PathVariable String cityId, @RequestBody Commodity commodity) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName(); // get the request user's username
		commodityExchange.supplyCommodity(cityId, username, commodity.getName());
	}
}
