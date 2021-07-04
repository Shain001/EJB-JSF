package fit5042.ass.controllers;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import fit5042.ass.repository.entities.Customer;

@RequestScoped
@Named(value = "contact")
public class Contact {
	private String phone;
	private String title;
	private String firstName;
	private String lastName;
	private String company;
	private String email;
	public Contact(String phone, String title, String firstName, String lastName, String company,String email) {
		super();
		this.phone = phone;
		this.title = title;
		this.firstName = firstName;
		this.lastName = lastName;
		this.company = company;
		this.email = email;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Contact() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	
}
