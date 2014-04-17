package com.gxtcookbook.code.client;

import java.util.ArrayList;
import java.util.List;

import com.extjs.gxt.ui.client.data.ListLoadConfig;
import com.extjs.gxt.ui.client.data.ListLoadResult;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.gxtcookbook.code.client.data.Response;
import com.gxtcookbook.code.server.model.Customer;
import com.gxtcookbook.code.server.model.Review;
import com.gxtcookbook.code.server.model.persisted.Course;
import com.gxtcookbook.code.server.model.persisted.Department;
import com.gxtcookbook.code.server.model.persisted.Student;
import com.gxtcookbook.code.server.model.persisted.mvp.DepartmentModel;


/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface RemoteGatewayAsync {	

	void listCustomers(ListLoadConfig cfg,
			AsyncCallback<ListLoadResult<Customer>> callback);

	void listReviews(ListLoadConfig cfg,
			AsyncCallback<ListLoadResult<Review>> callback);

	void saveCustomer(Customer customer, AsyncCallback<Void> callback);

	void getCustomer(int index, AsyncCallback<Customer> callback);

	void getCustomers(ListLoadConfig cfg, AsyncCallback<List<Customer>> callback);
	void getJSON(String url, AsyncCallback<String> callback);

	void deleteStudents(ArrayList<Long> losers, AsyncCallback<Response> callback);

	void saveStudents(ArrayList<Student> changes,
			AsyncCallback<Response> callback);

	void listCourses(ListLoadConfig cfg,
			AsyncCallback<ListLoadResult<Course>> callback);

	void listDepartments(ListLoadConfig cfg,
			AsyncCallback<ListLoadResult<Department>> callback);

	void listStudents(ListLoadConfig cfg,
			AsyncCallback<ListLoadResult<Student>> callback);

	void listDepartmentModels(ListLoadConfig cfg,
			AsyncCallback<ListLoadResult<DepartmentModel>> callback);

	void saveDepartments(ArrayList<DepartmentModel> changes,
			AsyncCallback<Response> callback);
	
	


	
}
