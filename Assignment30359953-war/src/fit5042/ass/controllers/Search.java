package fit5042.ass.controllers;

import javax.el.ELContext;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@RequestScoped
@Named("search")
public class Search {

	private Customer customer;
	private int cusId;
	private int conId;
	private String industry;
	private String cusName;
	private String industry1;
	private String cusName1;
	public String getIndustry1() {
		return industry1;
	}

	public void setIndustry1(String industry1) {
		this.industry1 = industry1;
	}

	public String getCusName1() {
		return cusName1;
	}

	public void setCusName1(String cusName1) {
		this.cusName1 = cusName1;
	}

	public String getCusName() {
		return cusName;
	}

	public void setCusName(String cusName) {
		this.cusName = cusName;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public int getConId() {
		return conId;
	}

	public void setConId(int conId) {
		this.conId = conId;
	}

	private CustomerApplication app;
	
	public Search() throws NumberFormatException, Exception {
        ELContext context
                = FacesContext.getCurrentInstance().getELContext();

        app = (CustomerApplication) FacesContext.getCurrentInstance()
                        .getApplication()
                        .getELResolver()
                        .getValue(context, null, "customerApplication");
        app.searchAllCustomer();
    }
	
	public void setApp(CustomerApplication app) {
		this.app = app;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public int getCusId() {
		return cusId;
	}

	public void setCusId(int cusId) {
		this.cusId = cusId;
	}
	

	public void searchCustomerById(int cusId) 
    {
       try
       {
            //search this property then refresh the list in PropertyApplication bean
            app.searchCustomerById(cusId);
       }
       catch (Exception ex)
       {
           
       }
    }
	
	public void searchContactById(int conId) 
    {
       try
       {
            //search this property then refresh the list in PropertyApplication bean
            app.searchContactById(conId);
       }
       catch (Exception ex)
       {
           
       }
    }
	
	public void searchCustomerByIndustry(String industry) {
		try {
			app.searchCustomerByIndustry(industry);
		}
		catch(Exception ex) {
			
		}
	}
	
	public void searchCustomerByName(String name) {
		try {
			app.searchCustomerByName(name);
		}
		catch(Exception ex) {
			
		}
	}
	
	public void searchContactByCompany(String name) {
		try {
			app.searchContactByCompany(name);
		}
		catch(Exception ex) {
			
		}
	}
	
	public void searchCusByCombine(String name, String industry) {
		try {
			app.searchCusByCombine(name, industry);
		}
		catch(Exception ex) {
			
		}
	}
}
