package grok.citysearch.service;

import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import grok.citysearch.loaders.CityLoader;
import grok.citysearch.model.City;
import grok.citysearch.repository.CityRepository;

@Service
public class CityService {

	@Autowired
	private CityRepository cityRepository;
	
	public List<City> findCities(){
		return cityRepository.findAll();
	}
	
	public City get(Long cityId) {
		return cityRepository.findOne(cityId);
	}
}
