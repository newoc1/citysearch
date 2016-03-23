package grok.citysearch.common.mediator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import grok.citysearch.city.City;
import grok.citysearch.city.CityService;
import grok.citysearch.commodity.Commodity;
import grok.citysearch.commodity.CommodityService;
import grok.citysearch.user.User;
import grok.citysearch.user.UserService;

@Service
@Transactional
public class CommodityExchange {

	private CityService cityService;
	private UserService userService;
	private CommodityService commodityService;
	private CommodityCourier commodityCourier;
	
	@Autowired
	public CommodityExchange(CityService cityService, UserService userService, CommodityService commodityService, CommodityCourier commodityCourier) {
		this.cityService = cityService;
		this.userService = userService;
		this.commodityService = commodityService;
		this.commodityCourier = commodityCourier;
	}
	
	public void supplyCommodity(String cityId, String username, String commodityName) {
		City city = cityService.get(cityId);
		User user = userService.get(username);
		Commodity commodity = commodityService.get(commodityName);
		user.supplyCommodity(commodityCourier, commodity, city);
		
	}
}
