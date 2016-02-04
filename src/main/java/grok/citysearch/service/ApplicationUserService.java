package grok.citysearch.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import grok.citysearch.model.ApplicationUser;
import grok.citysearch.repository.ApplicationUserRepository;

@Service
public class ApplicationUserService {

	@Autowired
	private ApplicationUserRepository applicationUserRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public void save(ApplicationUser applicationUser){
		applicationUser.setPassword(passwordEncoder.encode(applicationUser.getPassword()));
		applicationUserRepository.save(applicationUser);
	}
}
