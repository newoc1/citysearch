package grok.citysearch.security;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Email;

@Entity
@Table(name = "User")
public class ApplicationUser {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long id;

	@Column(name = "USERNAME", nullable=false)
	private String username;

	@Column(name = "PASSWORD", nullable=false)
	private String password;

	@Email
	@Column(name = "EMAIL", nullable=false)
	private String email;

	@Column(name="ACCOUNT_NON_EXPIRED", nullable=false)
	private boolean accountNonExpired;
	
	@Column(name="ACCOUNT_NON_LOCKED", nullable=false)
	private boolean accountNonLocked;
	
	@Column(name="CREDENTIALS_NON_EXPIRED", nullable=false)
	private boolean credentialsNonExpired;
	
	@Column(name="ENABLED", nullable=false)
	private boolean enabled;
	
	@ManyToMany
	private Set<ApplicationGrantedAuthority> applicationGrantedAuthorities;
	
	protected ApplicationUser(){
		
	}
	public ApplicationUser(String username, String password, String email, boolean enabled){
		this.username = username;
		this.password = password;
		this.email = email;
		this.accountNonExpired = true;
		this.accountNonLocked = true;
		this.credentialsNonExpired = true;
		this.enabled = enabled;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Set<ApplicationGrantedAuthority> getApplicationGrantedAuthorities() {
		return applicationGrantedAuthorities;
	}

	public void setApplicationGrantedAuthorities(Set<ApplicationGrantedAuthority> applicationGrantedAuthorities) {
		this.applicationGrantedAuthorities = applicationGrantedAuthorities;
	}
	
	
}
