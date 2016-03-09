package grok.citysearch.common.mediator;

import grok.citysearch.city.City;
import grok.citysearch.commodity.Commodity;

public class CommodityDelivery {

	private Commodity commodity;
	private City city;
	public CommodityDelivery(Commodity commodity, City city) {
		this.commodity = commodity;
		this.city = city;
	}
	public Commodity getCommodity() {
		return commodity;
	}

	public City getCity() {
		return city;
	}

}
