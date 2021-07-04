package fit5042.ass.validators;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import javax.el.ELContext;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;

import fit5042.ass.controllers.CustomerApplication;
import fit5042.ass.repository.entities.User;


@SuppressWarnings("rawtypes")
@FacesValidator("com.validate.test.userValidate")
public class userCreateValidation implements Validator{
	@Inject
	CustomerApplication app;



	public CustomerApplication getApp() {
		return app;
	}




	public void setApp(CustomerApplication app) {
		this.app = app;
	}




	public userCreateValidation() throws Exception {
		ELContext context
        = FacesContext.getCurrentInstance().getELContext();

		app  = (CustomerApplication) FacesContext.getCurrentInstance()
                .getApplication()
                .getELResolver()
                .getValue(context, null, "customerApplication");
	}




	@Override
	public void validate(FacesContext arg0, UIComponent arg1, Object value) throws ValidatorException {
		boolean flag = true;
		String userName = value.toString();
		ArrayList<User> users = app.getUsers();
		for (User u : users) {
			if (u.getUserName().equals(userName)) {
				flag = false;
			}
		}
		if(!flag) {
			FacesMessage msg = 
			        new FacesMessage("user has already exist", 
			            "user has already exist");
			      msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			      throw new ValidatorException(msg);
		}
	}
	
	 

}
