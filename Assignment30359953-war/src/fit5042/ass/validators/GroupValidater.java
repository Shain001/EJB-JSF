package fit5042.ass.validators;

import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@SuppressWarnings("rawtypes")
@FacesValidator("com.validate.test.groupValidator")
public class GroupValidater implements Validator{
	private ArrayList<String> group;
	
	
	public GroupValidater() {
		group = new ArrayList<String>();
		group.add("admin");
		group.add("user");
	}


	


	public ArrayList<String> getGroup() {
		return group;
	}





	public void setGroup(ArrayList<String> group) {
		this.group = group;
	}





	@Override
	public void validate(FacesContext arg0, UIComponent arg1, Object value) throws ValidatorException {
		boolean flag = false;
		for (String s : group) {
			if (value.toString().equals(s)) {
				flag = true;
			}
		}
		
		if (!flag) {
			FacesMessage msg = 
			        new FacesMessage("group validation failed.", 
			            "Group shoud be admin/user");
			      msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			      throw new ValidatorException(msg);
		}
		
	}

}
