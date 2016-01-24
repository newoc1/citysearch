package grok.citysearch;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CitySearchApplication {
	public static void main(String[] args) {
		SpringApplication.run(CitySearchApplication.class, args);
		
	}
	
	@Autowired
	private CityLoader cityLoader;
}
