package com.gxtcookbook.code.server.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.extjs.gxt.ui.client.data.BeanModelTag;

@SuppressWarnings("serial")
public class Customer implements BeanModelTag, Serializable {

	private int id;
	private int age;	
	
	private String name;
	private String email;
	private String gender;	
	private double purchases;

	private ArrayList<Review> reviews;

	public Customer() {
		reviews = new ArrayList<Review>();
	}

	public Customer(String name, String email, int age) {
		this(-1, name, email, age);
	}

	public Customer(int id, String name, String email, int age) {
		this();
		if(id <= 0){
			setId(++this.id);
		}else{
			setId(id);
		}
		
		this.age = age;
		this.email = email;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}		
	
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}		

	public double getPurchases() {
		return purchases;
	}

	public void setPurchases(double purchases) {
		this.purchases = purchases;
	}

	public ArrayList<Review> getReviews() {
		return reviews;
	}

	public void setReviews(ArrayList<Review> reviews) {
		this.reviews = reviews;
	}

	public void addReview(Review review){
		if(!reviews.contains(review)){
			review.setReviewer(this);
			reviews.add(review);
		}
	}

	public static List<Customer> forTest(){
		List<Customer> data = new ArrayList<Customer>();

		Customer customer = new Customer(1, "Wuddy Brown", "wuddyb@live.com", 32);
		customer.setGender("Male");
		customer.setPurchases(6500);
		customer.addReview(new Review(3, "Cool ...", "You really pulled this off very well"));
		customer.addReview(new Review(8, "Starting Mine", "Would like to do somthing in the humanities"));
		customer.addReview(new Review(9, "Ok music ...", "Think I'd rather write about African Music!"));
		data.add(customer);
		
		customer = new Customer(2, "Lilian Edeoghon", "edolian@live.com", 23);
		customer.setGender("Female");
		customer.setPurchases(3450);
		customer.addReview(new Review(4, "Long Overdue", "I told you, you can do it, and you proved me so so right ..."));
		data.add(customer);
		
		customer = new Customer(3, "Jite Okoh", "kojite@live.com", 36);
		customer.setGender("Male");
		customer.setPurchases(8200);
		customer.addReview(new Review(5, "Always Up To Something!", "U did this in secret abi, well very inspiring"));
		data.add(customer);
		
		customer = new Customer(4, "Jessica Ogbebor", "jj@live.com", 6);
		customer.setGender("Female");
		customer.setPurchases(5125);
		customer.addReview(new Review(6, "U Didn't Do My Assignment?", "Uncle 'chass' no wonder u have not been helping with my assignments"));
		data.add(customer);
		
		customer = new Customer(5, "Ashim Ossai-Ugba", "ashimo@live.com", 14);
		customer.setGender("Male");
		customer.setPurchases(3100);
		customer.addReview(new Review(7, "I want to write too!", "I've been wanting to write something too, now I have the right 'excuse' to start right away"));
		data.add(customer);

		return data;
	}

	@Override
	public String toString() {
		return getName();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
