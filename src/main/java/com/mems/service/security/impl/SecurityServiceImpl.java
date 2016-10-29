package com.mems.service.security.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.mems.service.security.interfaces.SecurityService;

@Service
public class SecurityServiceImpl implements SecurityService {
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserDetailsService userDetailsService;
	private UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken;
	private static final Logger logger = LoggerFactory.getLogger(SecurityServiceImpl.class);

	@Override
	public String findLoggedInUsername() {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (userDetails instanceof UserDetails) {
			return ((UserDetails) userDetails).getUsername();
		}

		return null;
	}

	@Override
	public boolean authenticat(String username, String password) {
		try {
			UserDetails userDetails = userDetailsService.loadUserByUsername(username);
			usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, password,
					userDetails.getAuthorities());
			authenticationManager.authenticate(usernamePasswordAuthenticationToken);
			return usernamePasswordAuthenticationToken.isAuthenticated();
		} catch (BadCredentialsException ex) {
			logger.error("Auto login failed:", ex.getMessage());
			return false;
		}
	}

	@Override
	public void autologin(String username, String password) {
		if (authenticat(username, password)) {
			SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			logger.debug(String.format("Auto login %s successfully!", username));
		}
	}
}
