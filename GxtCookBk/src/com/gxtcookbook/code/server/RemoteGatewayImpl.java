package com.gxtcookbook.code.server;


import org.apache.log4j.Logger;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Validation;

import net.sf.gilead.core.PersistentBeanManager;
import net.sf.gilead.core.hibernate.HibernateUtil;
import net.sf.gilead.gwt.GwtConfigurationHelper;
import net.sf.gilead.gwt.PersistentRemoteService;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.extjs.gxt.ui.client.data.BaseListLoadResult;
import com.extjs.gxt.ui.client.data.BasePagingLoadResult;
import com.extjs.gxt.ui.client.data.ListLoadConfig;
import com.extjs.gxt.ui.client.data.ListLoadResult;
import com.extjs.gxt.ui.client.data.PagingLoadConfig;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.gxtcookbook.code.client.RemoteGateway;
import com.gxtcookbook.code.client.data.LocalData;
import com.gxtcookbook.code.client.data.Response;
import com.gxtcookbook.code.client.data.Stock;
import com.gxtcookbook.code.server.model.Customer;
import com.gxtcookbook.code.server.model.Review;
import com.gxtcookbook.code.server.model.persisted.Course;
import com.gxtcookbook.code.server.model.persisted.Department;
import com.gxtcookbook.code.server.model.persisted.Student;
import com.gxtcookbook.code.server.model.persisted.mvp.DepartmentModel;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
//public class RemoteGatewayImpl extends PersistentRemoteService implements
public class RemoteGatewayImpl extends PersistentRemoteService implements
		RemoteGateway {
	private static final Logger log = Logger.getLogger(RemoteGatewayImpl.class);
	private HibernateUtil hibernateUtil = null;
	private PersistentBeanManager beanManager = null;
	
	public RemoteGatewayImpl() {
		super();
		
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		hibernateUtil = new HibernateUtil(sessionFactory);
		beanManager = GwtConfigurationHelper.initGwtStatelessBeanManager(hibernateUtil);
		setBeanManager(beanManager);
		
		Validation.byDefaultProvider().configure();	
	}

	
	
	@Override
	public ListLoadResult<Customer> listCustomers(ListLoadConfig cfg) {
		List<Customer> customerData = Customer.forTest();
		List<Customer> result = new ArrayList<Customer>();
		
		log.debug("in listCustomers");
		if(cfg != null){
			Object query = cfg.get("query");
			if(query != null && query.toString().length() >= 3){
				String nameToken = query.toString().toLowerCase().trim();
				for (Customer customer : customerData) {
					if(customer.getName().toLowerCase().startsWith(nameToken)){
						result.add(customer);
					}
				}
			} else {
				result = customerData;
			}
		} else {
			result = customerData;
		}
			 
		log.debug("returning : " + result.toString());
		return new BaseListLoadResult<Customer>(result);
	}
	
	@Override
	public ListLoadResult<Review> listReviews(ListLoadConfig cfg) {
		List<Review> reviews = new ArrayList<Review>();
		List<Customer> customers = Customer.forTest();
				
		if(cfg.getPropertyNames().contains("customer")){  // get reviews for a customer
			Object query = cfg.get("customer");
			if(query != null && query.toString().length() > 0){
				int customerId = Integer.parseInt( query.toString() );
				for (Customer customer : customers) {
					if(customer.getId() == customerId){
						reviews.addAll(customer.getReviews());
						break;
					}				
				}
			}				
		} else { // get all reviews for all customers
			for (Customer customer : customers) {
				reviews.addAll(customer.getReviews());
			}
		}
		
		return new BaseListLoadResult<Review>(reviews);
	}
	
	public void saveCustomer(Customer customer) {
		
	}
	@Override
	public Customer getCustomer(int index) {
		return Customer.forTest().get(index);
	}
	@Override
	public List<Customer> getCustomers(ListLoadConfig cfg) {
		return Customer.forTest();
	}
	
	@Override
	public String getJSON(String url) {
		StringBuilder out = new StringBuilder();
		try {
			URL theUrl = new URL(url);
			URLConnection conn = theUrl.openConnection();
	        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        
	        String inputLine;	        
	        while ((inputLine = in.readLine()) != null){
	        	out.append(inputLine);
	        }            
	        in.close();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return out.toString();
	}

	@Override
	public ListLoadResult<Course> listCourses(ListLoadConfig cfg) {
		JpaController<Long, Course> dao = new JpaController<Long, Course>() {};
		return new BaseListLoadResult<Course>( dao.entities() );
	}

	@Override
	public ListLoadResult<Department> listDepartments(ListLoadConfig cfg) {
		JpaController<Long, Department> dao = new JpaController<Long, Department>() {};
		return new BaseListLoadResult<Department>( dao.entities() );
	}

	@Override
	public ListLoadResult<DepartmentModel> listDepartmentModels(ListLoadConfig cfg) {
		JpaController<Long, DepartmentModel> dao = new JpaController<Long, DepartmentModel>() {};
		return new BaseListLoadResult<DepartmentModel>( dao.entities() );
	}
	
	@Override
	public Response saveDepartments(ArrayList<DepartmentModel> changes) {
		Response response = Response.get();
		JpaController<Long, DepartmentModel> dao = new JpaController<Long, DepartmentModel>() {};
		try{
			dao.edit(changes);
			response.OK();
		}catch(Exception ex) {
			response.ERR(ex.getMessage());
		}
		return response;
	}

	@Override
	public Response deleteStudents(ArrayList<Long> losers) {
		Response response = Response.get();
		JpaController<Long, Student> dao = new JpaController<Long, Student>() {};
		try{
			dao.delete(losers);
			response.OK();
		}catch(Exception ex) {
			response.ERR(ex.getMessage());
		}
		return response;
	}

	@Override
	public Response saveStudents(ArrayList<Student> changes) {
		Response response = Response.get();
		ArrayList<Student> fresh = new ArrayList<Student>();
		ArrayList<Student> modified = new ArrayList<Student>();
		JpaController<Long, Student> dao = new JpaController<Long, Student>() {};
		
		for(Student student : changes) {
			if(student.getId() == null){
				fresh.add(student);
			}else{
				modified.add(student);
			}
		}
		
		try{
			dao.create(fresh);
			dao.edit(modified);
			response.OK();
		}catch(Exception ex) {
			response.ERR(ex.getMessage());
		}
		return response;
	}

	@Override
	public ListLoadResult<Student> listStudents(ListLoadConfig cfg) {
		JpaController<Long, Student> dao = new JpaController<Long, Student>() {};	
		return new BaseListLoadResult<Student>( dao.entities() );
	}


	public PagingLoadResult<Stock> getCompanies(PagingLoadConfig config)
	{
		List<Stock> companiesList = LocalData.getCompanies();
		int offset = config.getOffset();
		
		int limit = companiesList.size();
	    if (config.getLimit() > 0) {
	      limit = Math.min(offset + config.getLimit(), limit);
	    }
		
		ArrayList<Stock> sublist = new ArrayList<Stock>();
		
		for(int i=offset; i < limit; ++i) {
			sublist.add(companiesList.get(i));
		}
			
		BasePagingLoadResult<Stock> result = new BasePagingLoadResult<Stock>(sublist,offset,companiesList.size());
		
		
		return(result);
	}
}
