/**
 * File: TestDao.java
 * Date: May 20, 2014
 * Author: I070659
 */
package test.spring;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author Oded Nissan
 *
 */

public class TestDao {
	
	private JdbcTemplate jdbc;
	
	@Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbc = new JdbcTemplate(dataSource);
    }

	public JdbcTemplate getJdbc() {
		return jdbc;
	}

	public void setJdbc(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}
	
	public void printAll() {
		
		List<Map<String,Object>> list = this.jdbc.queryForList("select * from departments");
		for(Map m : list) {
			System.out.println("row = " + m.toString());
		}
		
	}

}
