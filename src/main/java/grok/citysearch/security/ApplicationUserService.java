package grok.citysearch.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ApplicationUserService {

	@Autowired
	private ApplicationUserRepository applicationUserRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public ApplicationUser get(String username) {
		return applicationUserRepository.findOneByUsername(username);
	}
	
	public void save(ApplicationUser applicationUser){
		applicationUser.setPassword(passwordEncoder.encode(applicationUser.getPassword()));
		applicationUserRepository.save(applicationUser);
	}
	
	public List<ApplicationUser> find() {
		return applicationUserRepository.findAll();
	}
}
