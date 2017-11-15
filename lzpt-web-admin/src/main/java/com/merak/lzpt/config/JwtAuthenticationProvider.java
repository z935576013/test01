package com.merak.lzpt.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Service;

import com.merak.lzpt.util.AdminInfo;
import com.merak.lzpt.util.SessionUtil;

@Service
public class JwtAuthenticationProvider implements AuthenticationProvider {

	@Override
	public Authentication authenticate(Authentication arg0) {
		String token = (String) arg0.getPrincipal();

		AdminInfo adminInfo = SessionUtil.getAdminInfoFromToken(token);

		if (adminInfo != null) {
			return new PreAuthenticatedAuthenticationToken(adminInfo, null,
					getGrantedAuthorities(adminInfo.getAuthoritys()));
		} else {
			throw new UsernameNotFoundException("需要登录");
		}
	}

	private List<GrantedAuthority> getGrantedAuthorities(String authoritys) {
		List<GrantedAuthority> grantedAuthority = new ArrayList<GrantedAuthority>();
		if (authoritys != null) {
			Arrays.asList(authoritys.split(",")).forEach(au -> grantedAuthority.add(new SimpleGrantedAuthority(au)));
		}
		return grantedAuthority;
	}

	@Override
	public boolean supports(Class<?> arg0) {
		return PreAuthenticatedAuthenticationToken.class.isAssignableFrom(arg0);
	}

}
