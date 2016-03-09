package grok.citysearch.loaders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import grok.citysearch.security.ApplicationUser;
import grok.citysearch.security.ApplicationUserService;

@Component
public class ApplicationUserLoader implements Loader {

	@Autowired
	private ApplicationUserService applicationUserService;
	@Override
	public void populate() {
		ApplicationUser applicationUser = new ApplicationUser("admin", "admin", "admincitysearch123@fakegmail.com", true);
		applicationUserService.save(applicationUser);
		
	}

}
