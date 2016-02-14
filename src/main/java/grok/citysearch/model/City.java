package grok.citysearch.model;

import java.util.Set;
import java.util.UUID;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;

import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

@SolrDocument(solrCoreName = "collection1")
public class City {
	@Id
	@GeneratedValue
	@Indexed
	@Field
	private String id;
	
	@Field
	private String name;

	@Field("user_rank_required_i")
	private int userRankRequired;

	@Field("wanted_commodities_s")
	private Set<Commodity> wantedCommodities;
	
	@Field("population_count_l")
	private long populationCount;

	protected City() {

	}

	public City(String name, long populationCount) {
		this.setId(id);
		this.setName(name);
		this.populationCount = populationCount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	@PrePersist
	public void setId(String id) {
		  this.id = UUID.randomUUID().toString();
	}
	
	public int getUserRankRequired() {
		return userRankRequired;
	}

	public void setUserRankRequired(int userRankRequired) {
		this.userRankRequired = userRankRequired;
	}

	public Set<Commodity> getWantedCommodities() {
		return wantedCommodities;
	}

	public void setWantedCommodities(Set<Commodity> wantedCommodities) {
		this.wantedCommodities = wantedCommodities;
	}

	public long getPopulationCount() {
		return populationCount;
	}

	public void setPopulationCount(long populationCount) {
		this.populationCount = populationCount;
	}
}
