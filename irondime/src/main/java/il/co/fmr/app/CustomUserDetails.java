package il.co.fmr.app;

import java.util.Arrays;

import il.co.fmr.data.IronDimeUser;
import il.co.fmr.data.UserService;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsChecker;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetails implements UserDetailsService,
		UserDetailsChecker {
	
	@Autowired
   	UserService userService;


	@Override
	public void check(UserDetails arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		  System.out.println("loading user: " + username);
    	  IronDimeUser idu = userService.getUser(username);
    	  ToStringBuilder.reflectionToString(idu);
    	  GrantedAuthority authority = new SimpleGrantedAuthority("USER");
    	  User u = new User(idu.getUserId(), idu.getPassword(), true, true, true, true, Arrays.asList(authority));
    	  return(u);

	}

}
