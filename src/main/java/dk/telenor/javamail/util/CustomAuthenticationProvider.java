package dk.telenor.javamail.util;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import dk.telenor.javamail.dao.UserDao;
import dk.telenor.javamail.dto.UserDTO;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private UserDao userDao;

	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		String encryptPasswordString = null;
		try {
			encryptPasswordString = AesScramblerPassword.encryptPassword((String) authentication.getCredentials());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String password = encryptPasswordString;
		
		String username = authentication.getName();
		
		UserDTO user = userDao.getUserByEmail(username);
		
		if(user == null){
			throw new BadCredentialsException("Username not found.");
		}
		if(!password.equals(user.getPassword())){
			throw new BadCredentialsException("Wrong password.");
		}
		
		Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
		
		return new UsernamePasswordAuthenticationToken(user, password, authorities);
	}

	public boolean supports(Class<?> authentication) {
	
		return true;
	}
	
}
