package grok.citysearch.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import grok.citysearch.model.City;
import grok.citysearch.service.CityService;

@RestController
public class CityController {

	@Autowired
	private CityService cityService;
	
	@RequestMapping("/cities")
	public List<City> findCities() {
		return cityService.findCities(); 
	}
	
	@RequestMapping("cities/{cityId}")
	public City get(@PathVariable Long cityId) {
		return cityService.get(cityId);
	}
}
