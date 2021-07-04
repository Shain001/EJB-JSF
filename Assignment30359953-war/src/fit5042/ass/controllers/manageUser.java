package fit5042.ass.controllers;

import javax.el.ELContext;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import fit5042.ass.mbeans.CustomerManagedBean;

@RequestScoped
@Named("manageUser")
public class manageUser {
	@Inject
	CustomerManagedBean customerManagedBean;
	private boolean showForm = true;
    private User user;
    @Inject
    CustomerApplication app;
	public manageUser() {
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
	public boolean isShowForm() {
		return showForm;
	}
	public void setShowForm(boolean showForm) {
		this.showForm = showForm;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public CustomerApplication getApp() {
		return app;
	}
	public void setApp(CustomerApplication app) {
		this.app = app;
	}
	
	public void addUser(User localUser) {
		customerManagedBean.addUser(localUser);
		app.searchAllUser();
	}
    
    public void deleteUser(int userId) throws Exception {
    	customerManagedBean.removeUser(userId);
    	app.searchAllUser();
    }
}
