package grok.citysearch.common.mediator;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import grok.citysearch.city.City;
import grok.citysearch.city.CityRepository;
import grok.citysearch.commodity.CommodityConverter;
import grok.citysearch.commodity.SolrCommodity;

@Service
@Transactional
public class CommodityCourier {

	private CityRepository cityRepository;
	@Autowired
	public CommodityCourier(CityRepository cityRepository){
		this.cityRepository = cityRepository;
	}
	public void deliver(CommodityDelivery commodityDelivery){
		City city = commodityDelivery.getCity();
		SolrCommodity solrCommodity = CommodityConverter.convert(commodityDelivery.getCommodity());
		city.removeWantedSolrCommodity(solrCommodity);
		cityRepository.save(city);
	}
}
