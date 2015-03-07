package com.gxtcookbook.code.client;


import java.util.ArrayList;
import java.util.List;

import com.extjs.gxt.ui.client.data.ListLoadConfig;
import com.extjs.gxt.ui.client.data.ListLoadResult;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.gxtcookbook.code.client.data.Response;
import com.gxtcookbook.code.server.model.Customer;
import com.gxtcookbook.code.server.model.Review;
import com.gxtcookbook.code.server.model.persisted.Course;
import com.gxtcookbook.code.server.model.persisted.Department;
import com.gxtcookbook.code.server.model.persisted.Student;
import com.gxtcookbook.code.server.model.persisted.mvp.DepartmentModel;



/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("remotegateway")
public interface RemoteGateway extends RemoteService {
	void saveCustomer(Customer customer);
	ListLoadResult<Customer> listCustomers(ListLoadConfig cfg);	 
	List<Customer> getCustomers(ListLoadConfig cfg);


	ListLoadResult<Review> listReviews(ListLoadConfig cfg);
	Customer getCustomer(int index); 
	String getJSON(String url);
	
	/* Chapter 11 */
	public ListLoadResult<Course> listCourses(ListLoadConfig cfg);	
	
	public Response saveDepartments(ArrayList<DepartmentModel> changes);
	public ListLoadResult<Department> listDepartments(ListLoadConfig cfg);
	public ListLoadResult<DepartmentModel> listDepartmentModels(ListLoadConfig cfg);
	
	public Response deleteStudents(ArrayList<Long> losers);
	public Response saveStudents(ArrayList<Student> changes);
	public ListLoadResult<Student> listStudents(ListLoadConfig cfg);	


}
