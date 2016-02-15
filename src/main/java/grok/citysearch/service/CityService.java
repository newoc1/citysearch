package grok.citysearch.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import grok.citysearch.model.solr.City;
import grok.citysearch.repository.solr.CityRepository;

@Service
public class CityService {

	@Autowired
	private CityRepository cityRepository;
	
	public Page<City> findCities(Pageable pageable){
		return cityRepository.findAll(pageable);
	}
	
	public City get(String cityId) {
		return cityRepository.findOne(cityId);
	}
}
