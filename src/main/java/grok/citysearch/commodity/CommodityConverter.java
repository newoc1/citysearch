package grok.citysearch.commodity;

import grok.citysearch.city.City;

/**
 * Handles converting a {@link Commodity} into a suitable format for A {@link City} solr document.
 * @author ocapobianco
 *
 */
public class CommodityConverter {

	public static SolrCommodity convert(Commodity commodity){
		return new SolrCommodity(commodity.getId(), commodity.getName());
	}

}
