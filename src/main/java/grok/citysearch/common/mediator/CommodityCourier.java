package grok.citysearch.common.mediator;

import org.springframework.stereotype.Service;

import grok.citysearch.city.City;
import grok.citysearch.commodity.CommodityConverter;
import grok.citysearch.commodity.SolrCommodity;

@Service
public class CommodityCourier {

	public void deliver(CommodityDelivery commodityDelivery){
		City city = commodityDelivery.getCity();
		SolrCommodity solrCommodity = CommodityConverter.convert(commodityDelivery.getCommodity());
		city.removeWantedSolrCommodity(solrCommodity);
	}
}
