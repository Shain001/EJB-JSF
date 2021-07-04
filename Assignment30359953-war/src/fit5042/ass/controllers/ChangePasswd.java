package fit5042.ass.controllers;

import javax.el.ELContext;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import fit5042.ass.mbeans.CustomerManagedBean;

@Named(value = "changePasswd")
@RequestScoped
public class ChangePasswd {
	@Inject
	CustomerManagedBean customerManagedBean;
	
	@Inject
	CustomerApplication app;
	
	private String passwd;
	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public ChangePasswd() {
		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
		
		customerManagedBean = (CustomerManagedBean) FacesContext.getCurrentInstance().getApplication().getELResolver()
				.getValue(elContext, null, "customerManagedBean");
		app = (CustomerApplication) FacesContext.getCurrentInstance()
                .getApplication()
                .getELResolver()
                .getValue(elContext, null, "customerApplication");
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
	
	public void changePsswd(String passwd) throws Exception {
		String userName= app.getCurUserId();
		
		String hashedPassws = customerManagedBean.SHA(passwd,"SHA-256");
		fit5042.ass.repository.entities.User user = customerManagedBean.findUserByName(userName).get(0);
		String oldPaa = user.getPassWord();
		if (!oldPaa.equals(hashedPassws)) {
			
			
			user.setPassWord(hashedPassws);
			customerManagedBean.editUser(user);
		}
		else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("pass word cannot be same as current pass word"));
		}
		
		
	}
	
}
