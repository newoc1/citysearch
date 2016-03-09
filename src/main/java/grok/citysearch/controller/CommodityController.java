package grok.citysearch.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import grok.citysearch.commodity.Commodity;
import grok.citysearch.commodity.CommodityService;

@RestController
public class CommodityController {

	@Autowired
	private CommodityService commodityService;
	
	@RequestMapping("/commodities")
	public List<Commodity> findCommodities(){
		return commodityService.find();
	}
}
