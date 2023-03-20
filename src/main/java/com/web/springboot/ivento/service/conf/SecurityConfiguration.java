/**
 * 
 */
package com.web.springboot.ivento.service.conf;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import com.web.springboot.ivento.component.security.jwt.JwtAuthenticationEntryPointImpl;
import com.web.springboot.ivento.component.security.jwt.JwtAuthenticationOncePerRequestFilter;
import com.web.springboot.ivento.service.security.UserDetailsServiceImpl;
import com.web.springboot.ivento.service.security.jwt.JwtTokenService;

/**
 * @author mokht
 *
 */
@Configuration
@EnableMethodSecurity // No need to define prePostEnabled to true because default is true
public class SecurityConfiguration {

	private static final Logger logger = LoggerFactory.getLogger(SecurityConfiguration.class);

	private UserDetailsServiceImpl userDetailsService;

	private JwtAuthenticationEntryPointImpl unauthorizedHandler;

	private final JwtTokenService jwtTokenService;

	/**
	 * @param userDetailsService
	 * @param unauthorizedHandler
	 * @param jwtUtils
	 */
	public SecurityConfiguration(UserDetailsServiceImpl userDetailsService,
			JwtAuthenticationEntryPointImpl unauthorizedHandler, JwtTokenService jwtTokenService) {
		this.userDetailsService = userDetailsService;
		this.unauthorizedHandler = unauthorizedHandler;
		this.jwtTokenService = jwtTokenService;
	}

	@Bean
	JwtAuthenticationOncePerRequestFilter authenticationJwtTokenFilter() {
		return new JwtAuthenticationOncePerRequestFilter(userDetailsService, jwtTokenService);
	}

	@Bean
	DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

		authProvider.setUserDetailsService(userDetailsService);
		authProvider.setPasswordEncoder(passwordEncoder());

		return authProvider;
	}

	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
		return authConfig.getAuthenticationManager();
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

//		http.cors().and().csrf().disable().exceptionHandling().authenticationEntryPoint(unauthorizedHandler);

		http.cors().configurationSource(request -> {
			var cors = new CorsConfiguration();
			cors.setAllowedOrigins(List.of("http://192.168.0.2:3000", "http://localhost:3000",
					"https://invento-oculuszzz.netlify.app/"));
			cors.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
			cors.setAllowedHeaders(List.of("*"));
			return cors;
		}).and().csrf().disable().exceptionHandling().authenticationEntryPoint(unauthorizedHandler);

		http.authorizeHttpRequests().requestMatchers("/api/auth/refreshtoken").permitAll();
		http.authorizeHttpRequests().requestMatchers("/api/auth/**").permitAll();
		http.authorizeHttpRequests().requestMatchers("/api/test/**").permitAll();
		http.authorizeHttpRequests().requestMatchers("/api/auth/users/**").hasRole("ADMIN");
		http.authorizeHttpRequests().anyRequest().authenticated();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.authenticationProvider(authenticationProvider());
		http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}

}
