package fit5042.ass.validators;

import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@SuppressWarnings("rawtypes")
@FacesValidator("com.validate.test.stringOnlyValidator")
public class stringOnlyValidator implements Validator{
	
	public stringOnlyValidator() {
		
	}


	@Override
	public void validate(FacesContext arg0, UIComponent arg1, Object value) throws ValidatorException {
		boolean flag = true;
		for (int i = 0; i < value.toString().length(); i++) {
			if (!Character.isDigit(value.toString().charAt(i))) {
				flag = false;
			}
		}
		
		if (flag) {
			FacesMessage msg = 
			        new FacesMessage("String only", 
			            "String only");
			      msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			      throw new ValidatorException(msg);
		}
	}

}
