package fit5042.ass.restService;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.json.JsonObject;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sun.faces.action.RequestMapping;

import fit5042.ass.repository.CustomerRepository;
import fit5042.ass.repository.entities.Industry;
import java.io.Serializable;

@CrossOrigin(origins = "http://localhost:4200")
@Path("greeting")
@Named("helloWorld")
@SessionScoped
public class HelloWorld implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unused")
    @Context
    private UriInfo context;
    
    @Inject 
    private CustomerRepository cr; 

	public CustomerRepository getCr() {
		return cr;
	}

	public void setCr(CustomerRepository cr) {
		this.cr = cr;
	}

	/**
     * Default constructor. 
     */
    public HelloWorld() {
        // TODO Auto-generated constructor stub
    }

    @GET
    @Produces("text/html")
    public String getHtml() throws Exception {
    	List<Industry> indus = cr.getAllIndustry();
     return "<html><body>"
     		+ "<h1> AUSPrintings Pty Ltd </h1>"
    		+ "<p> @author : Shenyi Zhang <p>"
     		+ "<p> @id: 30359953<p>"
    		+ "contact: szha0177@student.monash.edu"
     		+ "</body></html>";
     
    }

    
    
    @GET
    @Path("/getallindustry")
    public List<Industry> testing() throws Exception {
    	
		return cr.getAllIndustry();
    	
    }
    
    @POST
    @Path("/updateIndustry")
    public void updateIndustry(@QueryParam("id") int id,  @QueryParam("name") String name) throws Exception {
    	Industry indus = cr.searchIndustryById(id);
    	indus.setIndustryName(name);
    	cr.editIndustry(indus);
    }
    
    

}