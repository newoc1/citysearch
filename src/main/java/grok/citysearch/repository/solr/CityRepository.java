package grok.citysearch.repository.solr;

import org.springframework.data.solr.repository.SolrCrudRepository;

import grok.citysearch.model.solr.City;

public interface CityRepository extends SolrCrudRepository<City, String>{

	City findByName(String name);
}
