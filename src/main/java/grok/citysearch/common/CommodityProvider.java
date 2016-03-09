package grok.citysearch.common;

import grok.citysearch.city.City;
import grok.citysearch.commodity.Commodity;
import grok.citysearch.common.mediator.CommodityCourier;

public interface CommodityProvider {

	boolean hasCommodity(Commodity commodity);
	
	void supplyCommodity(CommodityCourier commodityCourier, Commodity commodity, City city);
}
