package grok.citysearch.city;

import java.util.Collection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.repository.SolrCrudRepository;

public interface CityRepository extends SolrCrudRepository<City, String>{

	Page<City> findByNameStartingWith(Collection<String> name, Pageable pageable);
	
	Page<City> findByNameStartingWithAndUserRankRequiredLessThanEqual(Collection<String> name, Integer userNameRequired, Pageable pageable);
	
	Page<City> findByUserRankRequiredLessThanEqual(Integer userNameRequried, Pageable pageable);
	
	City findOneByIdAndUserRankRequiredLessThanEqual(String id, Integer userNameRequired);
}
