package grok.citysearch.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class City {
	@Id
	@GeneratedValue
	@Column(name = "ID")
	private Long id;
	
	@Column(name="NAME",unique = true, nullable = false)
	private String name;

	@Column(name = "USER_RANK_REQUIRED")
	private int userRankRequired;

	@ManyToMany
	@JoinTable(name = "CITY_COMMODITY", joinColumns = {
			@JoinColumn(name = "CITY_ID", referencedColumnName = "ID") }, inverseJoinColumns =
				{@JoinColumn(name = "COMMODITY_ID", referencedColumnName = "ID") })
	private List<Commodity> wantedCommodities;

	protected City() {

	}

	public City(String name) {
		this.setId(id);
		this.setName(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public int getUserRankRequired() {
		return userRankRequired;
	}

	public void setUserRankRequired(int userRankRequired) {
		this.userRankRequired = userRankRequired;
	}

	public List<Commodity> getWantedCommodities() {
		return wantedCommodities;
	}

	public void setWantedCommodities(List<Commodity> wantedCommodities) {
		this.wantedCommodities = wantedCommodities;
	}
}
