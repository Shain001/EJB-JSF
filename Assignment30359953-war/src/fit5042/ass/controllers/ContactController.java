package fit5042.ass.controllers;

import java.util.Set;

import javax.el.ELContext;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;


@Named(value = "contactController")
@RequestScoped	//why here use session scope?
public class ContactController {

	private int conId; 
	public int getConId() {
		return conId;
	}


	public void setConId(int conId) {
		this.conId = conId;
	}


	public void setContact(fit5042.ass.repository.entities.Contact contact) {
		this.contact = contact;
	}


	private fit5042.ass.repository.entities.Contact contact;
    

    public ContactController() throws Exception {
        // Assign Cus identifier via GET param 
        //this CusID is the index, don't confuse with the Cus Id
    	conId = Integer.valueOf(FacesContext.getCurrentInstance()
                .getExternalContext()
                .getRequestParameterMap()
                .get("conID"));  // cusID 
        // Assign Cus based on the id provided 
        
        contact = getContact();
    }

 
	public fit5042.ass.repository.entities.Contact getContact() throws Exception {
        if (contact == null) {
            // Get application context bean CusApplication 
            ELContext context
                    = FacesContext.getCurrentInstance().getELContext();
            CustomerApplication app
                    = (CustomerApplication) FacesContext.getCurrentInstance()
                            .getApplication()
                            .getELResolver()
                            .getValue(context, null, "customerApplication");
            // -1 to CusId since we +1 in JSF (to always have positive Cus id!) 
            return app.getContacts().get(--conId); //this CusId is the index, don't confuse with the Cus Id
        }
        return contact;
    }
	
	
}
