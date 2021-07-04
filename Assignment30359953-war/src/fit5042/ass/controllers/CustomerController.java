package fit5042.ass.controllers;

import java.util.Set;

import javax.el.ELContext;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import fit5042.ass.repository.entities.Industry;

@Named(value = "customerController")
@RequestScoped	//why here use session scope?
public class CustomerController {

	private int cusId; //this CusId is the index, don't confuse with the Cus Id
	private Set<fit5042.ass.repository.entities.Contact> contacts;
	private String indusName;
    public String getIndusName() {
		return indusName;
	}

	public void setIndusName(String indusName) {
		this.indusName = indusName;
	}

	public int getCusId() {
        return cusId;
    }

    public void setCusId(int CusId) {
        this.cusId = CusId;
    }
    private fit5042.ass.repository.entities.Customer customer;
    

    public CustomerController() throws Exception {
        // Assign Cus identifier via GET param 
        //this CusID is the index, don't confuse with the Cus Id
        cusId = Integer.valueOf(FacesContext.getCurrentInstance()
                .getExternalContext()
                .getRequestParameterMap()
                .get("cusID"));  // cusID 
        // Assign Cus based on the id provided 
        customer = getCustomer();
        contacts = customer.getContacts();
    }

    public Set<fit5042.ass.repository.entities.Contact> getContacts() {
		return contacts;
	}

	public void setContacts(Set<fit5042.ass.repository.entities.Contact> contacts) {
		this.contacts = contacts;
	}

	

	public void setCustomer(fit5042.ass.repository.entities.Customer customer) {
		this.customer = customer;
	}

	public fit5042.ass.repository.entities.Customer getCustomer() throws Exception {
        if (customer == null) {
            // Get application context bean CusApplication 
            ELContext context
                    = FacesContext.getCurrentInstance().getELContext();
            CustomerApplication app
                    = (CustomerApplication) FacesContext.getCurrentInstance()
                            .getApplication()
                            .getELResolver()
                            .getValue(context, null, "customerApplication");
            // -1 to CusId since we +1 in JSF (to always have positive Cus id!) 
            return app.getCustomers().get(--cusId); //this CusId is the index, don't confuse with the Cus Id
        }
        return customer;
    }
	
	
}
