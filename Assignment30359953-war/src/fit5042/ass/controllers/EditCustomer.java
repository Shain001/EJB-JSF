package fit5042.ass.controllers;

import javax.el.ELContext;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import fit5042.ass.mbeans.CustomerManagedBean;

@Named(value = "edit")
@RequestScoped
public class EditCustomer {
	private int cusId;
	private String indusName;
	private int conId;
	@Inject
	CustomerManagedBean customerManagedBean;
	
	@Inject
	CustomerApplication app;

	public EditCustomer() {
		cusId = 0;
		conId = 0;
		indusName = null;
		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
		
		customerManagedBean = (CustomerManagedBean) FacesContext.getCurrentInstance().getApplication().getELResolver()
				.getValue(elContext, null, "customerManagedBean");
		app = (CustomerApplication) FacesContext.getCurrentInstance()
                .getApplication()
                .getELResolver()
                .getValue(elContext, null, "customerApplication");
	}

	public int getConId() {
		return conId;
	}

	public void setConId(int conId) {
		this.conId = conId;
	}

	public int getCusId() {
		return cusId;
	}

	public void setCusId(int cusId) {
		this.cusId = cusId;
	}

	public String getIndusName() {
		return indusName;
	}

	public void setIndusName(String indusName) {
		this.indusName = indusName;
	}

	public CustomerManagedBean getCustomerManagedBean() {
		return customerManagedBean;
	}

	public void setCustomerManagedBean(CustomerManagedBean customerManagedBean) {
		this.customerManagedBean = customerManagedBean;
	}

	public CustomerApplication getApp() {
		return app;
	}

	public void setApp(CustomerApplication app) {
		this.app = app;
	}
	
	public void editCustomer(fit5042.ass.repository.entities.Customer customer, String indusName) throws Exception {
		customerManagedBean.editCustomer(customer, indusName);
		app.searchAllCustomer();
		app.searchAllContact();
		app.searchAllIndustry();
	}
	
	public void editContact(fit5042.ass.repository.entities.Contact contact) throws Exception {
		customerManagedBean.editContact(contact);
		app.searchAllCustomer();
		app.searchAllContact();
		app.searchAllIndustry();
	}
	
	public void removeCustomer(int cusId) throws Exception {
		customerManagedBean.removeCustomer(cusId);
		app.searchAllCustomer();
		app.searchAllContact();
		app.searchAllIndustry();
	}
	
	public void removeContact(int conId) throws Exception {
		customerManagedBean.removeContact(conId);
		app.searchAllCustomer();
		app.searchAllContact();
		app.searchAllIndustry();
	}
	public void removeIndustry(int insId) throws Exception {
		try {
			customerManagedBean.removeIndustry(insId);
			app.searchAllCustomer();
			app.searchAllContact();
			app.searchAllIndustry();
		}
		catch(Exception ex) {
			
		}
		
	}
	
	public void editIndustry(fit5042.ass.repository.entities.Industry indus, String indusName) throws Exception {
		customerManagedBean.editIndustry(indus, indusName);
	}
	
	public void editUser(fit5042.ass.repository.entities.User user, String userName) throws Exception {
		user.setUserName(userName);
		customerManagedBean.editUser(user);
	}
}
