package configuration;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import services.UserServices;

@Configuration
@EnableWebSecurity
@EnableWebMvc
@ComponentScan({ "service" })
public class SecurityConfig extends WebSecurityConfigurerAdapter {


	private static final Logger LOGGER = LoggerFactory.getLogger(SecurityConfig.class);

	@Autowired
	UserServices userService;
	//		 @Autowired
	//		 protected void configureGlobal(AuthenticationManagerBuilder auth) throws
	//		 Exception {
	//		 auth.jdbcAuthentication().dataSource(datasource)
	//		 .usersByUsernameQuery("select username, password, enabled" + " from users where username=?")
	//		 .authoritiesByUsernameQuery("select username, authority " + "from authorities where username=?")
	//		 .passwordEncoder(new BCryptPasswordEncoder());
	//		 }
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		auth
		.inMemoryAuthentication().passwordEncoder(encoder)
		.withUser("seb").password(encoder.encode("password")).roles("USER");
	}

	//	@Autowired
	//	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	//		auth.authenticationProvider(authenticationProvider());
	//	}
	//
	//	@Override
	//	@Bean
	//	public UserDetailsService userDetailsServiceBean() {
	//		LOGGER.info("bean detail service");
	//		return userService;
	//	}
	//	@Bean
	//	public BCryptPasswordEncoder passwordEncoder() {
	//		LOGGER.info("password encoder");
	//		return new BCryptPasswordEncoder();
	//	}
	//
	//	@Bean
	//	public DaoAuthenticationProvider authenticationProvider() {
	//		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
	//		authProvider.setUserDetailsService(userService);
	//		return authProvider;
	//	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		BasicAuthenticationEntryPoint entryPoint = new BasicAuthenticationEntryPoint();
		entryPoint.setRealmName("toto");
		http.csrf().disable();
		http.authorizeRequests().antMatchers("/webapp/**").permitAll();
		http.authorizeRequests().antMatchers("/dashboard").hasAnyRole("USER", "ADMIN");
		http.authorizeRequests().and().formLogin().loginProcessingUrl("/spring_security").loginPage("/login")
		.defaultSuccessUrl("/dashboard").failureUrl("/login?error=true");
	}
}