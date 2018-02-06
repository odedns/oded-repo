/**
 * 
 */
package il.co.fmr.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author oded
 *
 */

@Service
public class UserService {

	@Autowired
    private JdbcTemplate jdbcTemplate;

	
	
	public UserService()
	{
		
	}
	/**
	 * 
	 * @param user
	 */
	public void createUser(IronDimeUser user)
	{
		String query = "INSERT INTO USERS (userid, password,name,email,phone) values ( ? ,?,?,?,?)";
				
		jdbcTemplate.update(query, user.getUserId(),user.getPassword(),user.getName(),user.getEmail(),user.getPhone());
		
	}
	
	
	
	/**
	 * get user by userId
	 * @param userId
	 * @return
	 */
	public IronDimeUser getUser(String userId)
	{
		String query = "SELECT * FROM USERS where userId=?";
		
		IronDimeUser u = jdbcTemplate.queryForObject(query, new Object[] {userId}, new BeanPropertyRowMapper<IronDimeUser>(IronDimeUser.class));
		return(u);
	}
	
	/**
	 * login authentication
	 * @param user
	 * @param password
	 * @return
	 */
	public boolean login(String user, String password)
	{
		IronDimeUser u = getUser(user);
		return(u.getPassword().equals(password));
	}
	
	
	
}
