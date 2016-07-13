package test.spring;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import test.spring.data.RepoTestBean;

@Configuration
//@PropertySource("classpath:/jdbc.properties" )


@EnableJpaRepositories

public class AppConfig {

	 @Autowired
	    private Environment env;
	@Bean
	public RepoTestBean repoTest()
	{
		return(new RepoTestBean());
	}
	

	    @Bean
	    public TestBean testBean() {
	        return new TestBean();
	    }

	    @Bean
	    public SingletonBean singletonBean() {
	        return new SingletonBean();
	    }
	    @Bean
	    public AccountService accountBean() {
	        return new AccountServiceImpl();
	    }
	    @Bean
	    public DataSource dataSource() {
	        DriverManagerDataSource dataSource = new DriverManagerDataSource();
	        dataSource.setDriverClassName(env.getRequiredProperty("jdbc.driver"));
	        dataSource.setUrl(env.getRequiredProperty("jdbc.url"));
	        dataSource.setUsername(env.getRequiredProperty("jdbc.username"));
	        dataSource.setPassword(env.getRequiredProperty("jdbc.password"));
	        return dataSource;
	    }
}
