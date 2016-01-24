package grok.citysearch.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import grok.citysearch.model.City;
import grok.citysearch.repository.CityRepository;

@Service
public class CityService {

	@Autowired
	private CityRepository cityRepository;
	
	public List<City> findCities(){
		return cityRepository.findAll();
	}
}
