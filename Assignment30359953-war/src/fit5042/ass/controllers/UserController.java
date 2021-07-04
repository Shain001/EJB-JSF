package fit5042.ass.controllers;

import java.util.Set;

import javax.el.ELContext;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;


@Named(value = "userController")
@RequestScoped	//why here use session scope?
public class UserController {

	private String userName; 

	private int userId;

	private fit5042.ass.repository.entities.User user;
    

    public UserController() throws Exception {
        // Assign Cus identifier via GET param 
        //this CusID is the index, don't confuse with the Cus Id
    	userId = Integer.valueOf(FacesContext.getCurrentInstance()
                .getExternalContext()
                .getRequestParameterMap()
                .get("userID"));  // cusID 
        // Assign Cus based on the id provided 
        
    	user = getUser();
    }

 
	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	public void setUser(fit5042.ass.repository.entities.User user) {
		this.user = user;
	}


	public fit5042.ass.repository.entities.User getUser() throws Exception {
        if (user == null) {
            // Get application context bean CusApplication 
            ELContext context
                    = FacesContext.getCurrentInstance().getELContext();
            CustomerApplication app
                    = (CustomerApplication) FacesContext.getCurrentInstance()
                            .getApplication()
                            .getELResolver()
                            .getValue(context, null, "customerApplication");
            // -1 to CusId since we +1 in JSF (to always have positive Cus id!) 
            return app.getUsers().get(--userId); //this CusId is the index, don't confuse with the Cus Id
        }
        return user;
    }
	
	
}
