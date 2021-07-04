package fit5042.ass.controllers;

import javax.el.ELContext;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.faces.bean.ManagedProperty;
import fit5042.ass.mbeans.*;

@RequestScoped
@Named("addCustomer")
public class addCustomer {
	@Inject
	CustomerManagedBean customerManagedBean;
	
	public CustomerManagedBean getCustomerManagedBean() {
		return customerManagedBean;
	}

	public void setCustomerManagedBean(CustomerManagedBean customerManagedBean) {
		this.customerManagedBean = customerManagedBean;
	}

	private boolean showForm = true;
    private Customer customer;
    CustomerApplication app;
    
    // initialize the context configuration, get the application object and managebean object from context
    public addCustomer() 
    {
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
    
    public void addCustomer(Customer localCustomer) {
    	try {
    		// add the customer through customerMB where the ejb function is called
    		customerManagedBean.addCustomer(localCustomer,app.getCurrentUser());
    		// application class contains all customer fetched from db, after adding new customers to db, 
    		// application class shall be updated to still contain all customers
    		app.searchAllUser();
    		app.searchAllCustomer();
    		app.searchAllContact();
    		//FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Customer has been added succesfully"));
    	}
    	catch(Exception ex){
    		
    	}
    	// what does this showForm attribute mean?
    	showForm = true;
    }
}
