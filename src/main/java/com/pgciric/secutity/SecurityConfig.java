package com.pgciric.secutity;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.pgciric.jwt.JwtRequestFilter;
import com.pgciric.service.user_detail.MyUserDetailService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private DataSource dataSource;
	@Autowired
	private MyUserDetailService userDetailsService;
	@Autowired
	private JwtRequestFilter jwtRequestFilter;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource);
//		auth.userDetailsService(userDetailsService);
	}
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
//		http.csrf().disable()
//			.authorizeRequests().antMatchers("/api/authenticate", "swagger-ui.html#/").permitAll()
//			.anyRequest().authenticated()
//			.and().sessionManagement()
//			.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//		
//		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

		http
		.csrf().disable()
		.authorizeRequests()
		.antMatchers("/")
			.permitAll()
		.antMatchers("/admin/*")
			.hasAnyAuthority("ROLE_SUPERADMIN","ROLE_ADMIN")
		.anyRequest()
		.authenticated()
		.and()
		.formLogin()
			.loginPage("/loginPage")
			.defaultSuccessUrl("/admin/", true)
			.loginProcessingUrl("/authenticateTheUser")
			.permitAll()
		.and()
		.logout()
			.clearAuthentication(true)
			.invalidateHttpSession(true)
			.deleteCookies("auth_code","JSESSIONID")
			.logoutUrl("/logout")
			.logoutSuccessUrl("/loginPage")
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
		
		
//		.and()
//		.rememberMe().tokenRepository(persistentTokenRepository())
		//if csrf is enabled remove logoutRequestMatcher

	}
	
//	@Autowired
//	JpaConfiguration jpaConfig;
//
//	@Bean(name = "persistentTokenRepository")
//	public PersistentTokenRepository persistentTokenRepository() {
//	    JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
//	    tokenRepository.setDataSource(jpaConfig.dataSource());
//	    return tokenRepository;
//	}
	
//	@Override
//	@Bean
//	protected AuthenticationManager authenticationManager() throws Exception {
//		return super.authenticationManagerBean();
//	}
}
