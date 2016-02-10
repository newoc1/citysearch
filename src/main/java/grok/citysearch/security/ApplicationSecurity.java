package grok.citysearch.security;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class ApplicationSecurity extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/styles/**").permitAll().anyRequest().authenticated().and().formLogin()
				.loginPage("/login").permitAll().failureUrl("/login?error")
				.and().logout().logoutUrl("/logout").permitAll()
				.and().exceptionHandling().accessDeniedPage("/access?error");
	}

}
