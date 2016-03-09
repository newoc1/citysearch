package grok.citysearch.city;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import grok.citysearch.commodity.Commodity;

@Service
public class CityService {

	// regex to find character in the category (i.e. the \\p) of punctuation
	private static final Pattern IGNORED_CHARS_PATTERN = Pattern.compile("\\p{Punct}");

	@Autowired
	private CityRepository cityRepository;

	public Page<City> findCities(String name, Pageable pageable) {
		Page<City> cities;
		Collection<String> parsedNames = splitSearchTermAndRemoveIgnoredCharacters(name);
		if (name != null) {
			cities = cityRepository.findByNameStartingWith(parsedNames, pageable);
		} else {
			cities = cityRepository.findAll(pageable);
		}
		return cities;

	}

	/**
	 * Split on whitespace and then strip out punctuation
	 * 
	 * @param searchTerm
	 * @return
	 */
	private Collection<String> splitSearchTermAndRemoveIgnoredCharacters(String searchTerm) {
		List<String> result = new ArrayList<>();
		String[] searchTerms = StringUtils.split(searchTerm, " ");
		if (searchTerms == null) {
			searchTerms = new String[1];
			searchTerms[0] = searchTerm;
		}
		for (String term : searchTerms) {
			if (!StringUtils.isEmpty(term)) {
				result.add(IGNORED_CHARS_PATTERN.matcher(term).replaceAll(" "));
			}
		}
		return result;
	}

	public City get(String cityId) {
		return cityRepository.findOne(cityId);
	}
	
}
