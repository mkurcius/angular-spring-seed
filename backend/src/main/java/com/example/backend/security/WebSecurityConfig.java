package com.example.backend.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
				.withUser("user")
				.password("{noop}s3cr3t")
				.authorities("USER");
	}

	@Override
	protected void configure(final HttpSecurity http) throws Exception {
		http
				.authorizeRequests()
				.antMatchers("/api/**").authenticated()
				.anyRequest().permitAll()
				.and()
				.formLogin()
				.successHandler((request, response, authentication) -> response.sendRedirect("http://localhost:4200"))
				.and()
				.exceptionHandling()
				.defaultAuthenticationEntryPointFor(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED), new AntPathRequestMatcher("/api/**"));
	}
}
