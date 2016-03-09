package grok.citysearch.security;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("customUserDetailsService")
@Transactional
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private ApplicationUserRepository applicationUserRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		ApplicationUser applicationUser = applicationUserRepository.findOneByUsername(username);
		User user = new User(applicationUser.getUsername()
				, applicationUser.getPassword()
				, applicationUser.isEnabled()
				, applicationUser.isAccountNonExpired()
				, applicationUser.isCredentialsNonExpired()
				, applicationUser.isAccountNonLocked()
				, applicationUser.getApplicationGrantedAuthorities());
		return user;
	}

}
