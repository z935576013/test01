package com.merak.lzpt.config;

import javax.annotation.Resource;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter.XFrameOptionsMode;

import com.merak.lzpt.constants.AuthorityConstants;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Resource
	private AuthenticationProvider userAuthenticationProvider;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.headers().addHeaderWriter(new XFrameOptionsHeaderWriter(XFrameOptionsMode.SAMEORIGIN));
		http.authenticationProvider(userAuthenticationProvider);
		http.formLogin().loginPage("/login/login").permitAll().and().logout().permitAll();

		setAuthorizationConf(http);

	}

	private void setAuthorizationConf(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/product/**")
				.hasAnyAuthority(AuthorityConstants.DEFAULT_AUTHORITY.getAuthorityCode());
		http.authorizeRequests().antMatchers("/dem*", "/home").permitAll();
		http.authorizeRequests().anyRequest().hasAuthority(AuthorityConstants.DEFAULT_AUTHORITY.getAuthorityCode());
	}

}
