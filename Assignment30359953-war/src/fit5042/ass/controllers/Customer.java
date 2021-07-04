package fit5042.ass.controllers;

import java.util.HashSet;
import java.util.Set;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import fit5042.ass.repository.entities.Contact;
import fit5042.ass.repository.entities.User;

@RequestScoped
@Named(value = "customer")
public class Customer {
	private String industry;	// validation in the range?
	private String postCode;
	private String country;
	private String state;
	private String street;
	private String companyName;
	private int cusId;
	private User managedBy;
	
	
	public Customer(String industry, String postCode, String country, String state, String street, String companyName,
			int cusId, User managedBy) {
		super();
		this.industry = industry;
		this.postCode = postCode;
		this.country = country;
		this.state = state;
		this.street = street;
		this.companyName = companyName;
		this.cusId = cusId;
		this.managedBy = managedBy;
		
	}
	
	
	

	

	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}






	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public int getCusId() {
		return cusId;
	}

	public void setCusId(int cusId) {
		this.cusId = cusId;
	}

	public User getManagedBy() {
		return managedBy;
	}

	public void setManagedBy(User managedBy) {
		this.managedBy = managedBy;
	}

	
	
	
}
