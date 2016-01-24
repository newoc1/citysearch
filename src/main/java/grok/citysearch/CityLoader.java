package grok.citysearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import grok.citysearch.model.City;
import grok.citysearch.repository.CityRepository;

@Component
public class CityLoader {

	@Autowired
	private CityRepository cityRepository;

	@PostConstruct
	public void saveCities() throws URISyntaxException {
		int numberOfCities = 1000;
		System.out.println("Finding cities....");
		Charset charset = Charset.forName("US-ASCII");
		Path filePath = Paths.get(getClass().getClassLoader().getResource("cities.csv").toURI());

		try (BufferedReader reader = Files.newBufferedReader(filePath, charset)) {
			String line = null;
			int i = 0;
			while ((line = reader.readLine()) != null && i < numberOfCities) {
				String cityName = extractCityName(line);

				if (cityRepository.findByName(cityName) == null) {
					City city = new City(cityName);
					cityRepository.save(city);
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
