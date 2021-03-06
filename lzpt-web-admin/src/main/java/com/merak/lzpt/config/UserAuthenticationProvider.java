package com.merak.lzpt.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import com.merak.lzpt.util.AdminInfo;

@Service
public class UserAuthenticationProvider implements AuthenticationProvider {

	@Override
	public Authentication authenticate(Authentication arg0) {
		String loginId = (String) arg0.getPrincipal();
		String loginPwd = (String) arg0.getCredentials();

		// TODO
		// AdminInfo adminInfo = adminService.login(loginId, loginPwd);
		AdminInfo adminInfo = new AdminInfo();
		System.err.println("authenticate " + loginId);
		adminInfo.setId(1L);
		adminInfo.setName("bbb");

		// TODO
		String authoritys = "DEFAULT,bbb";
		adminInfo.setAuthoritys(authoritys);

		List<GrantedAuthority> grantedAuthority = getGrantedAuthorities(authoritys);

		// if (adminInfo != null) {
		return new UsernamePasswordAuthenticationToken(adminInfo, loginPwd, grantedAuthority);
		// } else {
		// throw new BadCredentialsException("密码不正确");
		// }
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
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(arg0);
	}

}
