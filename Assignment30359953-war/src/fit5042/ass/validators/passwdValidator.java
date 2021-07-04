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
@FacesValidator("com.validate.test.passwdValidator")
public class passwdValidator implements Validator{
	private User curUser;
	@Inject
	CustomerApplication app;
	
	public User getCurUser() {
		return curUser;
	}




	public void setCurUser(User curUser) {
		this.curUser = curUser;
	}




	public CustomerApplication getApp() {
		return app;
	}




	public void setApp(CustomerApplication app) {
		this.app = app;
	}




	public passwdValidator() throws Exception {
		ELContext context
        = FacesContext.getCurrentInstance().getELContext();

		app  = (CustomerApplication) FacesContext.getCurrentInstance()
                .getApplication()
                .getELResolver()
                .getValue(context, null, "customerApplication");
		curUser = app.getCurrentUser();
	}




	@Override
	public void validate(FacesContext arg0, UIComponent arg1, Object value) throws ValidatorException {
		boolean flag = false;
		String oldPass = curUser.getPassWord();
		String newPass = SHA(value.toString(),"SHA-256");
		
		if (oldPass.equals(newPass)) {
			FacesMessage msg = 
			        new FacesMessage("new password cannot be same with old one", 
			            "new password cannot be same with old one");
			      msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			      throw new ValidatorException(msg);
		}
		
	}
	
	 public String SHA(final String strText, final String strType)
	  {
	    // return
	    String strResult = null;
	 
	    // validation 
	    if (strText != null && strText.length() > 0)
	    {
	      try
	      {
	        // SHA start
	    
	        MessageDigest messageDigest = MessageDigest.getInstance(strType);
	        // string input
	        messageDigest.update(strText.getBytes());
	        // get byte
	        byte byteBuffer[] = messageDigest.digest();
	 
	        // convert byte
	        StringBuffer strHexString = new StringBuffer();
	        // iterate byte buffer
	        for (int i = 0; i < byteBuffer.length; i++)
	        {
	          String hex = Integer.toHexString(0xff & byteBuffer[i]);
	          if (hex.length() == 1)
	          {
	            strHexString.append('0');
	          }
	          strHexString.append(hex);
	        }
	        // get result
	        strResult = strHexString.toString();
	      }
	      catch (NoSuchAlgorithmException e)
	      {
	        e.printStackTrace();
	      }
	    }
	 
	    return strResult;
	  }

}
