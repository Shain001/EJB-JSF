package fit5042.ass.controllers;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named(value = "industry")
@RequestScoped
public class Industry {
	private String indusName;

	public Industry() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getIndusName() {
		return indusName;
	}

	public void setIndusName(String indusName) {
		this.indusName = indusName;
	}
	
}
