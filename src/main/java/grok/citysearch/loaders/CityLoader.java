package grok.citysearch.loaders;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import grok.citysearch.model.City;
import grok.citysearch.model.Commodity;
import grok.citysearch.repository.CityRepository;
import grok.citysearch.repository.CommodityRepository;

@Component
public class CityLoader implements Loader {

	@Autowired
	private CityRepository cityRepository;
	@Autowired
	private CommodityRepository commodityRepository;

	/**
	 * Assumes commodities are already loaded.
	 */
	public void populate(){
		int numberOfCities = 100;
		System.out.println("Creating cities....");
		Charset charset = Charset.forName("US-ASCII");
		Path filePath = null;
		try {
			filePath = Paths.get(getClass().getClassLoader().getResource("cities.csv").toURI());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Commodity> commodities = commodityRepository.findAll();
		
		System.out.println("All available commodities.");
		for(Commodity commodity: commodities){
			System.out.println(commodity.getName());
		}
		try (BufferedReader reader = Files.newBufferedReader(filePath, charset)) {
			String line = null;
			int i = 0;
			while ((line = reader.readLine()) != null && i < numberOfCities) {
				//need to skip header
				if (i != 0) {
					String cityName = extractCityName(line);

					if (cityRepository.findByName(cityName) == null) {
						City city = new City(cityName);
						city.setWantedCommodities(commodities);
						cityRepository.save(city);
						i++;
					}
				} else {
					i++;
				}
			}
		} catch (IOException ioe) {
			System.err.format("IOException: %s%n", ioe);
		}
	}

	/**
	 * Each line is of format {zipcode},{state abbreviation},{city
	 * name},{latitude},{longitude}. This method extracts the the city name.
	 * 
	 * @param line
	 * @return the city name
	 */
	public String extractCityName(String csvLine) {
		return csvLine.split(",")[2];
	}
	
}
