package grok.citysearch.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import grok.citysearch.model.City;

public interface CityRepository extends JpaRepository<City, Long>{

	City findByName(String name);
}
