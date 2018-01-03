package services;

import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import dao.UserRepository;
import model.User;

@Component
public class UserServices implements UserDetailsService{
	private static final Logger LOGGER = LoggerFactory.getLogger(UserServices.class);

	UserRepository userRepository;

	public UserServices(UserRepository ur) {
		LOGGER.info("build user service");
		userRepository=ur;
	}

	@Override
	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
		LOGGER.info("load user"+name+"gvgvgvgv");
		User user = userRepository.findByName(name);
		LOGGER.info(user.getName()+" "+ user.getPassword());
		return new org.springframework.security.core.userdetails.User(user.getName(),
				user.getPassword(),
				user.getRoles().stream()
				.map(x -> new SimpleGrantedAuthority(x.getName()))
				.collect(Collectors.toSet())
				);
	}

	public User createUser(User user) {
		LOGGER.info(user.getName()+" "+ user.getPassword());
		return userRepository.save(user);
	}
}