package grok.citysearch.repository.solr;

import java.util.Collection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.repository.SolrCrudRepository;

import grok.citysearch.model.solr.City;

public interface CityRepository extends SolrCrudRepository<City, String>{

	Page<City> findByNameStartingWith(Collection<String> name, Pageable pageable);
}
