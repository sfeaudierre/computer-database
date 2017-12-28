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

    private UserRepository userRepository;

	public UserServices(UserRepository ur) {
		LOGGER.info("build user service");
		userRepository=ur;
	}
	
	@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		LOGGER.info("loaddd user");
		User user = userRepository.findByName(username);
        return new org.springframework.security.core.userdetails.User(user.getName(),
                                                                      user.getPassword(),
                                                                      user.getRoles().stream()
                                                                                     .map(x -> new SimpleGrantedAuthority(x.getName()))
                                                                                     .collect(Collectors.toSet())
                                                                     );
    }
}