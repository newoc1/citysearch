package grok.citysearch.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.springframework.security.core.GrantedAuthority;

@Entity
public class ApplicationGrantedAuthority implements GrantedAuthority {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7418823631846850115L;

	@ManyToMany(mappedBy="applicationGrantedAuthorities")
	private Set<ApplicationUser> applicationUsers;

	@Id
	@Column(name = "AUTHORITY", unique = true, nullable = false)
	private String authority;

	protected ApplicationGrantedAuthority(){
		
	}
	public ApplicationGrantedAuthority(String authority) {
		this.authority = authority;
	}
	
	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return null;
	}

}
