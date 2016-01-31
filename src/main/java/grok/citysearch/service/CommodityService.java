package grok.citysearch.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import grok.citysearch.model.Commodity;
import grok.citysearch.repository.CommodityRepository;

@Service
@Transactional
public class CommodityService {

	@Autowired
	private CommodityRepository commodityRepository;

	public List<Commodity> find() {
		return commodityRepository.findAll();
	}
}
