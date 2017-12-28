package configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import services.UserServices;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	//	@Autowired
	//	DataSource datasource;
	//	
	@Autowired
	UserServices userService;
	//	 @Autowired
	//	 protected void configureGlobal(AuthenticationManagerBuilder auth) throws
	//	 Exception {
	//	 auth.jdbcAuthentication().dataSource(datasource)
	//	 .usersByUsernameQuery("select username, password, enabled" + " from users where username=?")
	//	 .authoritiesByUsernameQuery("select username, authority " + "from authorities where username=?")
	//	 .passwordEncoder(new BCryptPasswordEncoder());
	//	 }

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		auth
		.inMemoryAuthentication().passwordEncoder(encoder)
		.withUser("user").password(encoder.encode("password")).roles("USER");
	}

	//	@Autowired
	//	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	//		auth.userDetailsService(userService);
	//		auth.authenticationProvider(authenticationProvider());
	//	}
	//	@Bean
	//	public DaoAuthenticationProvider authenticationProvider() {
	//		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
	//		authenticationProvider.setUserDetailsService(userService);
	//		return authenticationProvider;
	//	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests().antMatchers("/webapp/**").permitAll();
		http.authorizeRequests().antMatchers("/dashboard").hasAnyRole("USER", "ADMIN");
		http.authorizeRequests().and().formLogin().loginProcessingUrl("/spring_security").loginPage("/login")
		.defaultSuccessUrl("/dashboard").failureUrl("/login?error=true");
	}
}