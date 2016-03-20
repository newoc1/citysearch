package grok.citysearch.user;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import grok.citysearch.city.City;
import grok.citysearch.commodity.Commodity;
import grok.citysearch.common.CommodityProvider;
import grok.citysearch.common.mediator.CommodityCourier;
import grok.citysearch.common.mediator.CommodityDelivery;

@Entity
@Table(name = "User")
public class User implements CommodityProvider{

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "USERNAME", nullable=false)
	private String username;
	
	@ManyToMany
	private List<Commodity> ownedCommodities;

	@Override
	public boolean hasCommodity(Commodity commodity) {
		return ownedCommodities.contains(commodity);
	}

	@Override
	public void supplyCommodity(CommodityCourier commodityCourier, Commodity commodity, City city) {
		if(hasCommodity(commodity)) {
			CommodityDelivery commodityDelivery = createCommodityDelivery(commodity, city);
			ownedCommodities.remove(commodity);
			commodityCourier.deliver(commodityDelivery);
		}
		
	}
	
	/**
	 * Exists only for future testing.
	 * @param commodity
	 * @param city
	 * @return
	 */
	private CommodityDelivery createCommodityDelivery(Commodity commodity, City city){
		return new CommodityDelivery(commodity, city);
	}
	
	public void receive(Commodity commodity) {
		this.ownedCommodities.add(commodity);
	}

	
}
