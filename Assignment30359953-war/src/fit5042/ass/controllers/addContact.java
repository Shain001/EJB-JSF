package fit5042.ass.controllers;

import javax.el.ELContext;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import fit5042.ass.mbeans.CustomerManagedBean;

@RequestScoped
@Named("addContact")
public class addContact {
	@Inject
	CustomerManagedBean customerManagedBean;
	@Inject
	CustomerApplication app;
	
	private int cusId;

	public addContact() {
		ELContext context
        = FacesContext.getCurrentInstance().getELContext();

		app  = (CustomerApplication) FacesContext.getCurrentInstance()
                .getApplication()
                .getELResolver()
                .getValue(context, null, "customerApplication");

		//instantiate propertyManagedBean
		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
		customerManagedBean = (CustomerManagedBean) FacesContext.getCurrentInstance().getApplication()
				.getELResolver().getValue(elContext, null, "customerManagedBean");
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

	public int getCusId() {
		return cusId;
	}

	public void setCusId(int cusId) {
		this.cusId = cusId;
	}
	
	public void addContact(Contact LocalContact) throws Exception {
		customerManagedBean.addContact(LocalContact);
		app.searchAllCustomer();
		app.searchAllContact();
	}
	
	
}
