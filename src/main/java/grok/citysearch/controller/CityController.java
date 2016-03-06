package grok.citysearch.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import grok.citysearch.model.Commodity;
import grok.citysearch.model.solr.City;
import grok.citysearch.service.CityService;

@RestController
public class CityController {

	@Autowired
	private CityService cityService;
	@Autowired
	private UserDetailsService userDetailsService;

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
	public void giveCommodity(@PathVariable String cityId, @RequestParam(required = false) Commodity commodity) {
		City city = cityService.get(cityId);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName(); // get logged in username
		UserDetails user = userDetailsService.loadUserByUsername(name);
		System.out.println(user.getUsername());
	}
}
