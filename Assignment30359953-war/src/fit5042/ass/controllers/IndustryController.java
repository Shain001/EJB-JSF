package fit5042.ass.controllers;

import java.util.Set;

import javax.el.ELContext;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;


@Named(value = "industryController")
@RequestScoped	//why here use session scope?
public class IndustryController {

	private String indusName; 
	public String getIndusName() {
		return indusName;
	}


	public void setIndusName(String indusName) {
		this.indusName = indusName;
	}


	public int getIndusId() {
		return indusId;
	}


	public void setIndusId(int indusId) {
		this.indusId = indusId;
	}


	public void setIndus(fit5042.ass.repository.entities.Industry indus) {
		this.indus = indus;
	}


	private int indusId;

	private fit5042.ass.repository.entities.Industry indus;
    

    public IndustryController() throws Exception {
        // Assign Cus identifier via GET param 
        //this CusID is the index, don't confuse with the Cus Id
    	indusId = Integer.valueOf(FacesContext.getCurrentInstance()
                .getExternalContext()
                .getRequestParameterMap()
                .get("indusID"));  // cusID 
        // Assign Cus based on the id provided 
        
        indus = getIndus();
    }

 
	public fit5042.ass.repository.entities.Industry getIndus() throws Exception {
        if (indus == null) {
            // Get application context bean CusApplication 
            ELContext context
                    = FacesContext.getCurrentInstance().getELContext();
            CustomerApplication app
                    = (CustomerApplication) FacesContext.getCurrentInstance()
                            .getApplication()
                            .getELResolver()
                            .getValue(context, null, "customerApplication");
            // -1 to CusId since we +1 in JSF (to always have positive Cus id!) 
            return app.getIndustries().get(--indusId); //this CusId is the index, don't confuse with the Cus Id
        }
        return indus;
    }
	
	
}
