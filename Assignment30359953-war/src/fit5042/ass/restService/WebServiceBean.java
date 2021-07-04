package fit5042.ass.restService;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import fit5042.ass.restServiceClient.WebServiceClient;

@Named(value = "webServiceBean")
@SessionScoped
public class WebServiceBean implements Serializable {
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private String name;
private WebServiceClient webServiceClient;
public WebServiceBean() {
}
public String getName() {
return name;
}
public void setName(String name) {
this.name = name;
}
public void setWebServiceClient() {
	webServiceClient = new WebServiceClient();
	webServiceClient.setPostName2(getName());
}
}
