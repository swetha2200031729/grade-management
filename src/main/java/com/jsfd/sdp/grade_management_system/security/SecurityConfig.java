package com.jsfd.sdp.grade_management_system.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.jsfd.sdp.grade_management_system.service.UserService;

@Configuration
public class SecurityConfig {
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
    
    @Bean
    DaoAuthenticationProvider authProvider(UserService userService) {
    	DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
    	auth.setUserDetailsService(userService);
    	auth.setPasswordEncoder(passwordEncoder);
    	return auth;
    }
    
    @Bean 
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    	http.authorizeHttpRequests(
    			configurer -> configurer
    			.requestMatchers("/register").permitAll()
    			.requestMatchers("/registerTeacher").permitAll()
    			.requestMatchers("/").permitAll()
    			.requestMatchers("/e/**").hasRole("Student")
    			.requestMatchers("/faculty/**").hasRole("Faculty")
    			.requestMatchers("/admin/**").hasRole("Admin")
    			.requestMatchers("/assignments/faculty/show-assignment-details/**").hasRole("Faculty")
    			.requestMatchers("/registrations/upload-registration-file").hasRole("Faculty")
    			.anyRequest().authenticated()
			)
			.formLogin(form ->
			        form.loginPage("/login")
			            .loginProcessingUrl("/authenticate")
			            .defaultSuccessUrl("/")
			            .permitAll()
			)
			.logout(logout -> logout.permitAll())
			.exceptionHandling(configurer ->
			        configurer.accessDeniedPage("/access-denied")
			);
    	
    	http.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
		return http.build();
    }
    
}
