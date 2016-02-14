package grok.citysearch.repository;

import org.springframework.data.solr.repository.SolrCrudRepository;

import grok.citysearch.model.City;

public interface CityRepository extends SolrCrudRepository<City, Long>{

	City findByName(String name);
}
