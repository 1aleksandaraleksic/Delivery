package com.pgciric.secutity;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private DataSource dataSource;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.jdbcAuthentication().dataSource(dataSource);
	}
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
		.csrf().disable()
		.authorizeRequests()
		.antMatchers("/")
			.permitAll()
		.antMatchers("/admin/*")
			.hasAnyAuthority("ROLE_SUPERADMIN","ROLE_ADMIN")
	//	.anyRequest()
	//	.authenticated()
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
	
}
