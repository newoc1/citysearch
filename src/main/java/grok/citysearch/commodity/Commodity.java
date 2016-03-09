package grok.citysearch.commodity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Commodity {
	@Id
	@GeneratedValue
	@Column(name = "ID")
	private Long id;

	@Column(name="NAME",nullable = false)
	private String name;

	protected Commodity() {

	}

	public Commodity(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

}
