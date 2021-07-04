package fit5042.ass.controllers;

import java.util.ArrayList;
import java.util.Set;

import javax.el.ELContext;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import fit5042.ass.mbeans.CustomerManagedBean;
import fit5042.ass.repository.entities.Customer;
import fit5042.ass.repository.entities.User;

@Named(value = "allocate")
@RequestScoped
public class AllocateCustomer {

	private int cusId;
	private int userId;
	private String userName;
	private String cusName;
	@Inject
	CustomerManagedBean customerManagedBean;
	
	@Inject
	CustomerApplication app;
	
	public CustomerApplication getApp() {
		return app;
	}

	public void setApp(CustomerApplication app) {
		this.app = app;
	}

	public AllocateCustomer() throws Exception {
		cusId = 0;
		userId = 0;
		userName = null;
		cusName = null;
		// instantiate propertyManagedBean
		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
	
		customerManagedBean = (CustomerManagedBean) FacesContext.getCurrentInstance().getApplication().getELResolver()
				.getValue(elContext, null, "customerManagedBean");
		app = (CustomerApplication) FacesContext.getCurrentInstance()
                .getApplication()
                .getELResolver()
                .getValue(elContext, null, "customerApplication");
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCusName() {
		return cusName;
	}

	public void setCusName(String cusName) {
		this.cusName = cusName;
	}

	public int getCusId() {
		return cusId;
	}

	public void setCusId(int cusId) {
		this.cusId = cusId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public CustomerManagedBean getCustomerManagedBean() {
		return customerManagedBean;
	}

	public void setCustomerManagedBean(CustomerManagedBean customerManagedBean) {
		this.customerManagedBean = customerManagedBean;
	}
	
	public void allocateCustomer(String userName, String cusName) throws Exception {
		Customer customer = customerManagedBean.findCustomerByName(cusName).get(0);
		User user = customerManagedBean.findUserByName(userName).get(0);
		if (customer.getManagedBy() != null && !customer.getManagedBy().equals(user)) {
			user.addCustomer(customer);
			customerManagedBean.editUser(user);
			User oldManager = customer.getManagedBy();
			Set<Customer> temp = oldManager.getCustomers();
			temp.remove(customer);
			oldManager.setCustomers(temp);
			customerManagedBean.editUser(oldManager);
			customer.setManagedBy(user);
			customerManagedBean.editCustomer(customer);
			
		}
		else if (customer.getManagedBy() != null && customer.getManagedBy().equals(user)) {
			
		}
		else {
			user.addCustomer(customer);
			customerManagedBean.editUser(user);
			customer.setManagedBy(user);
			customerManagedBean.editCustomer(customer);

		}

		
		app.searchAllCustomer();
		app.searchAllUser();
		
	}
}
