package grok.citysearch.loaders;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import grok.citysearch.model.Commodity;
import grok.citysearch.model.solr.City;
import grok.citysearch.model.solr.SolrCommodity;
import grok.citysearch.repository.CommodityRepository;
import grok.citysearch.repository.solr.CityRepository;
import grok.citysearch.solr_converter.CommodityConverter;

@Component
public class CityLoader implements Loader {

	@Autowired
	private CityRepository cityRepository;
	@Autowired
	private CommodityRepository commodityRepository;
	@Autowired
	private Random random;
	
	private int numberOfCities;
	private int cityCommoditiesCount;
	private long maxPopulationCount;

	public CityLoader(int numberOfCities, int cityCommoditiesCount, long maxPopulationCount){
		this.numberOfCities = numberOfCities;
		this.cityCommoditiesCount = cityCommoditiesCount;
		this.maxPopulationCount = maxPopulationCount;
	}
	/**
	 * Assumes commodities are already loaded.
	 */
	public void populate(){
		System.out.println("Creating cities....");
		Charset charset = Charset.forName("US-ASCII");
		Path filePath = null;
		try {
			filePath = Paths.get(getClass().getClassLoader().getResource("cities.csv").toURI());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Commodity> availableCommodities = commodityRepository.findAll();
		cityRepository.deleteAll();
		try (BufferedReader reader = Files.newBufferedReader(filePath, charset)) {
			String line = null;
			int i = 0;
			while ((line = reader.readLine()) != null && i < numberOfCities) {
				//need to skip header
				if (i != 0) {
					String cityName = extractCityName(line);

					if (cityRepository.findByName(cityName) == null) {
						long populationCount = (long) (random.nextDouble()*maxPopulationCount);
						City city = new City(cityName, populationCount);
						Set<Commodity> wantedCommodities = chooseRandomCommodities(cityCommoditiesCount, availableCommodities);
						
						for(Commodity commodity: wantedCommodities) {
							city.addWantedSolrCommodity(CommodityConverter.convert(commodity));
						}
						
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
	
	public Set<Commodity> chooseRandomCommodities(int commoditiesCount, List<Commodity> availableCommodities){
		Set<Commodity> commodities = new HashSet<>();
		for(int i = 0; commodities.size() < commoditiesCount; i++){
			int commodityIndex = random.nextInt(availableCommodities.size()-1);
			commodities.add(availableCommodities.get(commodityIndex));	
		}
		return commodities;
	}
	
}
