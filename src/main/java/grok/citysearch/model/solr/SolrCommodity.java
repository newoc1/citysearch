package grok.citysearch.model.solr;

import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.solr.core.mapping.SolrDocument;

@SolrDocument(solrCoreName = "collection1")
public class SolrCommodity {
	
	@Field("commodity_id_l")
	private Long id;
	
	@Field("commodity_name_s")
	private String name;
	
	protected SolrCommodity(){
		
	}
	
	public SolrCommodity(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
