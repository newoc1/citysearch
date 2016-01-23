package grok.citysearch.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import grok.citysearch.model.City;

@RestController
public class CityController {

	@RequestMapping("/cities")
	public List<City> findCities() {
		List<City> cities = new ArrayList<>();
		cities.add(new City("New York"));
		return cities;
	}
}
