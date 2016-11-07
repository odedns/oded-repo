package il.co.fmr.app;


import il.co.fmr.data.IronDimeUser;
import il.co.fmr.data.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

//@SpringBootApplication // same as 
@Configuration 
@EnableAutoConfiguration 
@ComponentScan(basePackages = "il.co.fmr.rest")
public class Application {

	
	public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


	@Bean
	public CustomUserDetails customUserDetails()
	{
		return(new CustomUserDetails());
	}

	
	@Bean
	public UserService userService()
	{
		return(new UserService());
	}
	
       @EnableWebSecurity
    @Configuration
    static class WebSecurityConfig extends WebSecurityConfigurerAdapter {
     
      @Override
      protected void configure(HttpSecurity http) throws Exception {
    	  http.authorizeRequests().
    	    antMatchers("/user/test").permitAll().
    	    antMatchers("/user/login").permitAll().
    	    antMatchers("/user/register").permitAll().
    	    antMatchers("/user/**").authenticated().
    	    anyRequest().fullyAuthenticated().
    	    and().
    	    httpBasic().and().
    	    csrf().disable();
    	 
      }
    }
       
       @Configuration
    protected static class AuthenticationSecurity extends
    	GlobalAuthenticationConfigurerAdapter {

	@Autowired
	private CustomUserDetails customUserDetails;

	@Override
	public void init(AuthenticationManagerBuilder auth) throws Exception {
		System.out.println("in init()");
		auth.userDetailsService(customUserDetails);
	}
}
    
}





