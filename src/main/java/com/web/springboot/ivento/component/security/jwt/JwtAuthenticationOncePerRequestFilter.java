package com.web.springboot.ivento.component.security.jwt;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.web.springboot.ivento.service.security.UserDetailsServiceImpl;
import com.web.springboot.ivento.service.security.jwt.JwtTokenService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * @author mokht
 *
 *         Implementation of OncePerRequestFilter interface class. This class
 *         responsibilities to handle single execution token authenticate per
 *         request by user.
 *
 */
@Component
public class JwtAuthenticationOncePerRequestFilter extends OncePerRequestFilter {

	private static final Logger log = LoggerFactory.getLogger(JwtAuthenticationOncePerRequestFilter.class);

	private final JwtTokenService jwtTokenService;

	private final UserDetailsServiceImpl userDetailsServiceImpl;

	/**
	 * @param userDetailsServiceImpl
	 * @param jwtUtils
	 */
	public JwtAuthenticationOncePerRequestFilter(UserDetailsServiceImpl userDetailsServiceImpl,
			JwtTokenService jwtTokenService) {

		this.userDetailsServiceImpl = userDetailsServiceImpl;
		this.jwtTokenService = jwtTokenService;

	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String uri = request.getRequestURI();

		if (uri.contains("/api/auth/refreshtoken")) {

			filterChain.doFilter(request, response);
			return;

		}

		String jwt = parseJwt(request);

		// Validate token header authentication and security authentication
		if (jwt == null || SecurityContextHolder.getContext().getAuthentication() == null) {

			filterChain.doFilter(request, response);
			return;

		}

		if (jwtTokenService.validateJWTToken(jwt)) {

			String username = jwtTokenService.getUsernameFromToken(jwt);
			UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(username);

			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails,
					null, userDetails.getAuthorities());
			authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

			SecurityContextHolder.getContext().setAuthentication(authentication);

		}

		filterChain.doFilter(request, response);
	}

	private String parseJwt(HttpServletRequest request) {

		String headerAuth = request.getHeader("Authorization");

		if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer-")) {

			return headerAuth.substring(7, headerAuth.length());

		}

		return null;
	}

}
