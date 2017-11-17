package com.merak.lzpt.config;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter.XFrameOptionsMode;

import com.merak.lzpt.constants.AuthorityConstants;
import com.merak.lzpt.filter.AccessLogFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Resource
	private AuthenticationProvider userAuthenticationProvider;

	@Resource
	private AuthenticationProvider tokenAuthenticationProvider;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		TokenPreAuthenticationFilter tokenFilter = new TokenPreAuthenticationFilter();
		List<AuthenticationProvider> providers = new ArrayList<AuthenticationProvider>();
		providers.add(tokenAuthenticationProvider);
		ProviderManager authenticationManager = new ProviderManager(providers);
		tokenFilter.setAuthenticationManager(authenticationManager);
		http.addFilterAt(tokenFilter, AbstractPreAuthenticatedProcessingFilter.class);

		http.addFilterBefore(new AccessLogFilter(), AbstractPreAuthenticatedProcessingFilter.class);
		
//		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//		http.setSharedObject(RequestCache.class, new TokenRequestCache());
		
		http.csrf().disable();
		http.headers().addHeaderWriter(new XFrameOptionsHeaderWriter(XFrameOptionsMode.SAMEORIGIN));
		http.authenticationProvider(userAuthenticationProvider);
		http.formLogin().loginPage("/login/login").successHandler(new TokenAuthenticationSuccessHandler()).permitAll()
				.and().logout().permitAll();
		
		setAuthorizationConf(http);

	}

	private void setAuthorizationConf(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/product/**")
				.hasAnyAuthority(AuthorityConstants.DEFAULT_AUTHORITY.getAuthorityCode());
		http.authorizeRequests().antMatchers("/dem*", "/home").permitAll();
		http.authorizeRequests().anyRequest().hasAuthority(AuthorityConstants.DEFAULT_AUTHORITY.getAuthorityCode());
	}

}
