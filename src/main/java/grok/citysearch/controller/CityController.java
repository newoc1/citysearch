package grok.citysearch.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import grok.citysearch.model.solr.City;
import grok.citysearch.service.CityService;

@RestController
public class CityController {

	@Autowired
	private CityService cityService;
	
	@RequestMapping("/cities")
	public Page<City> findCities( @RequestParam(required=false) String name, Pageable pageable) {
		return cityService.findCities(name, pageable); 
	}
	
	
	@RequestMapping("cities/{cityId}")
	public City get(@PathVariable String cityId) {
		return cityService.get(cityId);
	}
}
