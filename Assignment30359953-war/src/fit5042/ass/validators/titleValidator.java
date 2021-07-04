package fit5042.ass.validators;

import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@SuppressWarnings("rawtypes")
@FacesValidator("com.validate.test.titleValidator")
public class titleValidator implements Validator{
	private ArrayList<String> title;
	private Object temp;
	
	public titleValidator() {
		title = new ArrayList<String>();
		title.add("Mr");
		title.add("Mrs");
		title.add("Miss");
		title.add("Doctor");
		
	}


	


	public ArrayList<String> getTitle() {
		return title;
	}


	public void setTitle(ArrayList<String> title) {
		this.title = title;
	}

	@Override
	public void validate(FacesContext arg0, UIComponent arg1, Object value) throws ValidatorException {
		
		boolean flag = false;
		for (String s : title) {
			if (value.toString().equals(s)) {
				flag = true;
			}
		}
		
		
		if (!flag) {
			FacesMessage msg = 
			        new FacesMessage("Title shoud be Mr/Mrs/Miss/Doctor", 
			            "Title shoud be Mr/Mrs/Miss/Doctor");
			      msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			      throw new ValidatorException(msg);
		}
		
	}

}
