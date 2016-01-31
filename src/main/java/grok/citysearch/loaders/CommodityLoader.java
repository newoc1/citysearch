package grok.citysearch.loaders;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import grok.citysearch.model.Commodity;
import grok.citysearch.repository.CommodityRepository;

@Component
public class CommodityLoader implements Loader {

	@Autowired
	private CommodityRepository commodityRepository;

	@Override
	public void populate() {
		ObjectMapper objectMapper = new ObjectMapper();
		Path path = null;
		try {
			path = Paths.get(getClass().getClassLoader().getResource("commodities.json").toURI());
			List<Commodity> commodity = objectMapper.readValue(path.toFile(),  new TypeReference<List<Commodity>>(){});
			commodityRepository.save(commodity);
		} catch (URISyntaxException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

}
