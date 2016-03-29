package grok.citysearch.user;

import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.Table;

import grok.citysearch.city.City;
import grok.citysearch.commodity.Commodity;
import grok.citysearch.common.CommodityProvider;
import grok.citysearch.common.mediator.CommodityCourier;
import grok.citysearch.common.mediator.CommodityDelivery;

@Entity
@Table(name = "User")
public class User implements CommodityProvider {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long id;

	@Column(name = "USERNAME", nullable = false)
	private String username;
	
	@Column(name = "USER_RANK")
	private Integer rank = 1;

    @ElementCollection
    @CollectionTable(name = "COMMODITY_SUPPLY")
    @Column(name = "commodityCount")
    @MapKeyJoinColumn(name = "commoditySupplyId", referencedColumnName = "id")
	private Map<Commodity, Integer> ownedCommodities;

	@Override
	public boolean hasCommodity(Commodity commodity) {
		return ownedCommodities.containsKey(commodity) && ownedCommodities.get(commodity) >0;
	}

	@Override
	public void supplyCommodity(CommodityCourier commodityCourier, Commodity commodity, City city) {
		if (hasCommodity(commodity)) {
			CommodityDelivery commodityDelivery = createCommodityDelivery(commodity, city);
			ownedCommodities.put(commodity, ownedCommodities.get(commodity)-1);
			commodityCourier.deliver(commodityDelivery);
		} else {
			throw new NoCommodityException("User does not have commodity"+commodity.getName());
		}
	}

	/**
	 * Exists only for future testing.
	 * 
	 * @param commodity
	 * @param city
	 * @return
	 */
	private CommodityDelivery createCommodityDelivery(Commodity commodity, City city) {
		return new CommodityDelivery(commodity, city);
	}

	public void receive(Commodity commodity) {
		if(ownedCommodities.containsKey(commodity)) {
			ownedCommodities.put(commodity, ownedCommodities.get(commodity)+1);
		} else {
			ownedCommodities.put(commodity, 1);
		}
	}

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}
}
