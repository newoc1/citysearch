package grok.citysearch.commodity;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CommodityService {

	@Autowired
	private CommodityRepository commodityRepository;

	public List<Commodity> find() {
		return commodityRepository.findAll();
	}
	
	public Commodity get(Long commodityId) {
		return commodityRepository.getOne(commodityId);
	}
}
