package fit5042.ass.controllers;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;

import fit5042.ass.mbeans.CustomerManagedBean;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import fit5042.ass.repository.entities.Customer;
import fit5042.ass.repository.entities.Industry;
import fit5042.ass.repository.entities.User;

import javax.el.ELContext;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

@Named(value = "customerApplication")
@SessionScoped
public class CustomerApplication implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	CustomerManagedBean customerManagedBean;
	
	private  String curUserId;
	private ArrayList<Industry> industries;
	public ArrayList<Customer> getCustomers() throws Exception {
//		updateCustomerList();
		return customers;
	}

	public CustomerManagedBean getCustomerManagedBean() {
		return customerManagedBean;
	}

	public void setCustomerManagedBean(CustomerManagedBean customerManagedBean) {
		this.customerManagedBean = customerManagedBean;
	}

	private ArrayList<Customer> customers;
	private ArrayList<fit5042.ass.repository.entities.Contact> contacts;
	private boolean showForm = true;
	private ArrayList<User> users;

	public boolean isShowForm() {
		return showForm;
	}

	public CustomerApplication() throws Exception {
		customers = new ArrayList<Customer>();
		users = new ArrayList<User>();
		industries = new ArrayList<Industry>();
		contacts = new ArrayList<fit5042.ass.repository.entities.Contact>();
		// instantiate propertyManagedBean
		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
		customerManagedBean = (CustomerManagedBean) FacesContext.getCurrentInstance().getApplication().getELResolver()
				.getValue(elContext, null, "customerManagedBean");

		// get properties from db
		curUserId = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
		searchAllUser();
		searchAllCustomer();
		searchAllContact();
		searchAllIndustry();
		// this user id is actually the name of user not id
		

	}


	public void searchAllIndustry() {
		industries.clear();

		for (fit5042.ass.repository.entities.Industry indus : customerManagedBean.getAllIndustry()) {
			
			industries.add(indus);
			
		}

		setIndustries(industries);
		
	}

	public ArrayList<Industry> getIndustries() {
		return industries;
	}

	public void setIndustries(ArrayList<Industry> industries) {
		this.industries = industries;
	}

	public void updateUserList() {
		// TODO Auto-generated method stub
		if (users != null && users.size() > 0) {
			
		} else {
			users.clear();

			for (fit5042.ass.repository.entities.User user : customerManagedBean.getAllUser()) {
				
					users.add(user);
				
			}

			setUsers(users);
		}
	}

	public ArrayList<User> getUsers() {
		updateUserList();
		return users;
	}

	public void setUsers(ArrayList<User> users) {
		this.users = users;
	}

	private void setCustomers(ArrayList<Customer> customers2) {
		// TODO Auto-generated method stub
		customers = customers2;
	}

	public void searchAllUser() {
		users.clear();

		for (fit5042.ass.repository.entities.User user : customerManagedBean.getAllUser()) {
			
				users.add(user);
			
		}

		setUsers(users);
	}

	public void searchAllCustomer() throws NumberFormatException, Exception {
		customers.clear();
		if (curUserId.equals("admin")) {
			for (fit5042.ass.repository.entities.Customer customer : customerManagedBean.getAllCustomer()) {
				customers.add(customer);
			}

			setCustomers(customers);
		}
		else {
			for (fit5042.ass.repository.entities.Customer customer : customerManagedBean.getCustomerByUser(getCurrentUser())) {
				customers.add(customer);
			}

			setCustomers(customers);
		}
		
	}
	
	public void searchAllContact() throws NumberFormatException, Exception {
		
		contacts.clear();
	
		if (curUserId.equals("admin")) {
			for (fit5042.ass.repository.entities.Contact contact : customerManagedBean.getAllContact()) {
				
				contacts.add(contact);
			}
			setContacts(contacts);
		}
		else {
			for (fit5042.ass.repository.entities.Customer customer : customerManagedBean.getCustomerByUser(getCurrentUser())) {
				for (fit5042.ass.repository.entities.Contact contact : customer.getContacts()) {
					contacts.add(contact);
			}
			setContacts(contacts);
		}
		}
		
	}
	
	

	public ArrayList<fit5042.ass.repository.entities.Contact> getContacts() {
		return contacts;
	}

	public void setContacts(ArrayList<fit5042.ass.repository.entities.Contact> contacts) {
		this.contacts = contacts;
	}

	public void searchCustomerById(int id) throws Exception {
		customers.clear();
		ArrayList<Customer> temp = new ArrayList<Customer>();
		Customer cus = customerManagedBean.findCustomerById(id);
		if (getCurrentUser().getGroup().equals("admin")){
				temp.add(cus);
		}
		else {
				if (cus.getManagedBy().getUserName().equals(this.curUserId))
					temp.add(cus);
			}
		
		setCustomers(temp);
	}
	
	public void updateCustomerList() throws Exception{
		if (customers != null && customers.size() > 0) {

		} else {
			customers.clear();

			for (fit5042.ass.repository.entities.Customer customer : customerManagedBean.getAllCustomer()) {
				customers.add(customer);
			}

			setCustomers(customers);
		}
	}
	
	public List<SelectItem> getUserSelect(){
		List<SelectItem> userSelect = new ArrayList<SelectItem>();
		for (User u : users) {
			if (u.getUserName().toLowerCase() != "admin") {
				userSelect.add(new SelectItem(u.getUserName()));
			}
		}
		return userSelect;
	}
	
	public List<SelectItem> getCustomerSelect(){
		List<SelectItem> cusSelect = new ArrayList<SelectItem>();
		for (Customer c : customers) {
			String index = c.getCompanyName();
			cusSelect.add(new SelectItem(index));
		}
		return cusSelect;
	}
	
	public List<SelectItem> getCustomerSelectByName(){
		List<SelectItem> cusSelect = new ArrayList<SelectItem>();
		for (Customer c : customers) {
			cusSelect.add(new SelectItem(c.getCompanyName()));
		}
		return cusSelect;
	}
	
	public List<SelectItem> getIndustryList(){
		List<SelectItem> indusSelect = new ArrayList<SelectItem>();
		for (Industry i : industries) {
			indusSelect.add(new SelectItem(i.getIndustryName()));
		}
		return indusSelect;
	}

	public String getCurUserId() {
		return curUserId;
	}

	public void setCurUserId(String curUserId) {
		this.curUserId = curUserId;
	}
	
	public User getCurrentUser() throws NumberFormatException, Exception {
		return customerManagedBean.findUserByName(curUserId).get(0);
	}

	public void searchContactById(int conId) throws Exception {
		contacts.clear();
        fit5042.ass.repository.entities.Contact contact = customerManagedBean.findContactById(conId);
        if (getCurrentUser().getGroup().equals("admin")) {
        	contacts.add(contact);
        }
        else if (contact.getCompany().getManagedBy().getUserName().equals(this.curUserId))
        	contacts.add(contact);
        setCustomers(customers);
		
	}

	public void searchCustomerByIndustry(String industry) throws Exception {
		customers.clear();
		ArrayList<Customer> temp = new ArrayList<Customer>();
		List<Customer> cus = customerManagedBean.findCustomerByIndustry(industry);
		if (getCurrentUser().getGroup().equals("admin")) {
			for (Customer c : cus) {
				temp.add(c);
			}
		}
		else {
			for (Customer c : cus) {
				if (c.getManagedBy().getUserName().equals(this.curUserId))
					temp.add(c);
			}
		}
		
		setCustomers(temp);
	}

	public void searchCustomerByName(String name) throws Exception {
		customers.clear();
		ArrayList<Customer> temp = new ArrayList<Customer>();
		List<Customer> cus = customerManagedBean.findCustomerByName(name);
		if (getCurrentUser().getGroup().equals("admin")) {
			for (Customer c : cus) {
				temp.add(c);
			}
		}
		else {
			for (Customer c : cus) {
				if (c.getManagedBy().getUserName().equals(this.curUserId))
					temp.add(c);
			}
		}
		
		setCustomers(temp);
		
	}

	public void searchContactByCompany(String name) throws Exception {
		contacts.clear();
		ArrayList<fit5042.ass.repository.entities.Contact> temp = new ArrayList<fit5042.ass.repository.entities.Contact>();
		Customer cus = customerManagedBean.findCustomerByName(name).get(0);
		Set<fit5042.ass.repository.entities.Contact> con = cus.getContacts();
		if (getCurrentUser().getGroup().equals("admin")) {
			for (fit5042.ass.repository.entities.Contact c : con) {
				temp.add(c);
			}
		}
		else if (cus.getManagedBy().getUserName().equals(this.curUserId)) {
			for (fit5042.ass.repository.entities.Contact c : con) {
				temp.add(c);
			}
		}
		
		setContacts(temp);
		
	}

	public void searchCusByCombine(String name, String industry) throws NumberFormatException, Exception {
		customers.clear();
		ArrayList<Customer> temp = new ArrayList<Customer>();
		List<Customer> cus = customerManagedBean.findCustomerByCombine(name,industry);
		if (getCurrentUser().getGroup().equals("admin")){
			for (Customer c : cus) {
				temp.add(c);
			}
		}
		else {
			for (Customer c : cus) {
				if (c.getManagedBy().getUserName().equals(this.curUserId))
					temp.add(c);
			}
		}
		
		setCustomers(temp);
		
	}
	
	public void logout() {

		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
		try {
			HttpSession session = request.getSession();
			session.invalidate();
			FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
			request.logout();
			
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect("http://localhost:8080/Assignment30359953-war/");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
