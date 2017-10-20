package com.merak.lzpt.config;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import com.merak.lzpt.constants.AuthorityConstants;
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
		// if (adminInfo != null) {
		return new UsernamePasswordAuthenticationToken(adminInfo, loginPwd,
				getUserAuthorities(adminInfo.getId().toString()));
		// } else {
		// throw new BadCredentialsException("密码不正确");
		// }
	}

	private Collection<GrantedAuthority> getUserAuthorities(String userId) {
		Collection<GrantedAuthority> grantedAuthority = new ArrayList<GrantedAuthority>();
		// TODO
		// AdminInfo admin = adminService.getAdmin(Long.valueOf(userId));
		AdminInfo admin = new AdminInfo();
		admin.setName("bbb");
		SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(
				AuthorityConstants.DEFAULT_AUTHORITY.getAuthorityCode());
		grantedAuthority.add(simpleGrantedAuthority);

		SimpleGrantedAuthority adminTypeAuthority = new SimpleGrantedAuthority(admin.getName().toString());
		grantedAuthority.add(adminTypeAuthority);

		return grantedAuthority;
	}

	@Override
	public boolean supports(Class<?> arg0) {
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(arg0);
	}

}
