package com.example.demo;



import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable()//需要關閉CSRF 不然post會403
			
			.authorizeRequests()
				.antMatchers("/resources/**").permitAll()
				//.antMatchers("/#","/", "/os", "/webjars/**", "/js/**","/dist/**").permitAll()
				.antMatchers("/","/123", "/webjars/**", "/js/**","/dist/**").permitAll()
				.antMatchers("/os/**").hasRole("admin")
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.loginPage("/")
				.loginProcessingUrl("/login")
				.successHandler(successHandler())
		        .failureHandler(failureHandler())
				.permitAll()
				.and()
			.logout()
				.permitAll();
	}
	
	 @Bean
	 public CorsFilter corsFilter() {
	        final UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
	        final CorsConfiguration cors = new CorsConfiguration();
	        cors.setAllowCredentials(true);
	        cors.addAllowedOrigin("*");
	        cors.addAllowedHeader("*");
	        cors.addAllowedMethod("*");
	        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", cors);
	        return new CorsFilter(urlBasedCorsConfigurationSource);
	  }
	 
	 private AuthenticationSuccessHandler successHandler() {
		    return new AuthenticationSuccessHandler() {
		      @Override
		      public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
		        httpServletResponse.getWriter().append("OK");
		    	httpServletResponse.setStatus(200);
		      }
		    };
	 }
	 private AuthenticationFailureHandler failureHandler() {
		    return new AuthenticationFailureHandler() {
		      @Override
		      public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
		        httpServletResponse.getWriter().append("Authentication failure");
		        httpServletResponse.setStatus(401);
		      }
		     };
	 }
	/*@Bean
	@Override
	public UserDetailsService userDetailsService() {
		
		UserDetails user =
			 User.withDefaultPasswordEncoder()
				.username("ushow")
				.password("vshow2351700")
				.roles("USER")
				.build();

		return new InMemoryUserDetailsManager(user);
	}*/
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {
		// TODO Auto-generated method stub
		auth //Builder Design Pattern
			.inMemoryAuthentication() //自訂Runtime時的使用者帳號
				.passwordEncoder(new BCryptPasswordEncoder())
				.withUser("ushow")
				.password(new BCryptPasswordEncoder().encode("vshow2351700"))
				.roles("USER")
				.and()
				.withUser("admin")
				.password(new BCryptPasswordEncoder().encode("admin"))
				.roles("admin");
	}
	
	 
}