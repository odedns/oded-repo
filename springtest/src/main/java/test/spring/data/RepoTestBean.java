package test.spring.data;

import org.springframework.beans.factory.annotation.Autowired;



public class RepoTestBean {

	@Autowired
	private DepartmentRepository repo;
	public RepoTestBean()
	{
		
	}
	public DepartmentRepository getRepo() {
		return repo;
	}
	public void setRepo(DepartmentRepository repo) {
		this.repo = repo;
	}
}
