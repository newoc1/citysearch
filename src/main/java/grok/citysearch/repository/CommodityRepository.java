package grok.citysearch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import grok.citysearch.model.Commodity;

@Repository
public interface CommodityRepository extends JpaRepository<Commodity, Long> {

}
