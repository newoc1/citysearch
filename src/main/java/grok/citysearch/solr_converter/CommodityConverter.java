package grok.citysearch.solr_converter;

import grok.citysearch.model.Commodity;
import grok.citysearch.model.solr.City;
import grok.citysearch.model.solr.SolrCommodity;

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
