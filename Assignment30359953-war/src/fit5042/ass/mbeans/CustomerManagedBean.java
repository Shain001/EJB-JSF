package fit5042.ass.mbeans;


import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.ejb.EJB;
import javax.el.ELContext;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import fit5042.ass.controllers.CustomerApplication;
import fit5042.ass.repository.CustomerRepository;
import fit5042.ass.repository.entities.Contact;
import fit5042.ass.repository.entities.Customer;
import fit5042.ass.repository.entities.Industry;
import fit5042.ass.repository.entities.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named(value = "customerManagedBean")
@SessionScoped	//why here use session scope?
public class CustomerManagedBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@EJB
	CustomerRepository customerRepository;
	

	/**
     * Creates a new instance of CustomerManagedBean
     */
	public CustomerManagedBean() {
		
		
	}
	
	public void addCustomer(Customer Customer) 
    {
        try {
        	customerRepository.addCustomer(Customer);
        } catch (Exception ex) {
            Logger.getLogger(CustomerManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
		
    }
	
	public void addContact(Contact contact,Customer customer) 
    {
        try {
        	customerRepository.addContact(contact);
        } catch (Exception ex) {
            Logger.getLogger(CustomerManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
	
	
	
	public void addContact(fit5042.ass.controllers.Contact localContact) throws Exception {
		Contact contact = new Contact();
		String phone = localContact.getPhone();
		String title = localContact.getTitle();
		String firstName = localContact.getFirstName();
		String lastName = localContact.getLastName();
		String companyName = localContact.getCompany();
		String email = localContact.getEmail();
		//set contact entity
		contact.setEmail(email);
		contact.setFirstName(firstName);
		contact.setLastName(lastName);
		contact.setPhone(phone);
		contact.setTitle(title);
		Customer customer = findCustomerByName(companyName).get(0);
		contact.setCompany(customer);
		Set<Contact> contacts = customer.getContacts();
		contacts.add(contact);
		customer.setContacts(contacts);
		
		editCustomer(customer);
		
	}
	
	
	public List<Customer> getAllCustomer(){
		try {
            List<Customer> customers = customerRepository.getAllCustomer();
            return customers;
        } catch (Exception ex) {
            Logger.getLogger(CustomerManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
	}
	
	public List<User> getAllUser(){
		try {
            List<User> users = customerRepository.getAllUsers();
            return users;
        } catch (Exception ex) {
            Logger.getLogger(CustomerManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
	}
	
	public List<Contact> getAllContact(){
		try {
            List<Contact> contacts = customerRepository.getAllContact();
            return contacts;
        } catch (Exception ex) {
            Logger.getLogger(CustomerManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
	}
	
	public List<Industry> getAllIndustry(){
		try {
            List<Industry> industrys = customerRepository.getAllIndustry();
            return industrys;
        } catch (Exception ex) {
            Logger.getLogger(CustomerManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
	}
	
	public void addCustomer(fit5042.ass.controllers.Customer localCustomer,User user) throws Exception {
        //convert this newCustomer which is the local Customer to entity Customer
		
        try {
        	// add the customer to db and get the id of the newly added customer to fetch it back from db in order to add
        	// the contact person
        	Customer customer = convertCustomerToEntity(localCustomer,user);
        	addCustomer(customer);
        	
        } catch (Exception ex) {
            Logger.getLogger(CustomerManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
	
	// Convert the customer type from controller plain Java object to Entity type to write in DB
	private Customer convertCustomerToEntity(fit5042.ass.controllers.Customer localCustomer,User user) throws Exception {
		
        Customer customer = new Customer(); //entity
       
        // build the customer entity
        String postCode = localCustomer.getPostCode();
        String country = localCustomer.getCountry();
        String state = localCustomer.getState();
        String street = localCustomer.getStreet();
        String companyName = localCustomer.getCompanyName();
        String indusName = localCustomer.getIndustry();
        Industry industry = findIndustryByName(indusName);
        
        // add info to customer entity's attribute
        customer.setPostCode(postCode);
        customer.setCountry(country);
        customer.setState(state);
        customer.setStreet(street);
        customer.setCompanyName(companyName);
        customer.setIndustry(industry);
        customer.setRegisterDate(new Date());
        
        // allocate customer to current user when being created
        customer.setManagedBy(user);
        Set<Customer> customers = new HashSet<Customer>();
        customers = user.getCustomers();
        customers.add(customer);
        user.setCustomers(customers);
        //add info to contact entity's attribute
        return customer;
    }
	
	private Industry findIndustryByName(String name) throws Exception {
		return customerRepository.searchIndustryByName(name).get(0);
	}

	public User findUserById(int id) throws Exception {
		return customerRepository.findUserById(id);
	}
	
	public List<User> findUserByName(String name) throws Exception {
		return customerRepository.findUserByName(name);
	}
	
	public Customer findCustomerById(int id) throws Exception {
		return customerRepository.searchCustomerById(id);
	}
	
	public void editCustomer(Customer customer,String indusName) throws Exception {
		if (!indusName.isEmpty()) {
			Industry indus = findIndustryByName(indusName);
			
			customer.setIndustry(indus);
		}
		
		customerRepository.updateCustomer(customer);
		
	}
	
	public void editCustomer(Customer customer) throws Exception {
		customerRepository.updateCustomer(customer);
		
	}
	
	public void editUser(User user) throws Exception {
		customerRepository.updateUser(user);
		
	}
	
	public void removeCustomer(int cusId) throws Exception {
		customerRepository.deleteCustomerById(cusId);
	}
	
	public void removeContact(int conId) throws Exception {
		customerRepository.deleteContactById(conId);
	}
	
	public void removeUser(int userId) throws Exception {
		User u = findUserById(userId);
		if (u.getCustomers().size() == 0)
			customerRepository.deleteUserById(userId);
		else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Allocate customer managed by this user first"));
		}
	}
	
	public void removeIndustry(int insId) throws Exception {
		customerRepository.deleteIndustryById(insId);
	}
	
	public void addUser(User user) {
		try {
        	customerRepository.addUser(user);
        } catch (Exception ex) {
            Logger.getLogger(CustomerManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
	}
	
	public void addUser(fit5042.ass.controllers.User localUser) {
		try {
        	
        	User user = convertUserToEntity(localUser);
        	addUser(user);
        	
        } catch (Exception ex) {
            Logger.getLogger(CustomerManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
	}

	private User convertUserToEntity(fit5042.ass.controllers.User localUser) {
		String userName = localUser.getUserName().toLowerCase();
		String passwd = localUser.getPasswd();
		String groups = localUser.getGroups();
		String hashedWd = SHA(passwd,"SHA-256");
		User user = new User();
		user.setGroup(groups);
		user.setPassWord(hashedWd);
		user.setUserName(userName);
		return user;
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
	 
	 public List<Customer> findCustomerByName(String cusName) throws Exception {
		 
		return customerRepository.searchCustomerByName(cusName);
	 }

	public Contact findContactById(int conId) throws Exception{
		
		return customerRepository.searchContactById(conId);
	}

	public void addIndustry(fit5042.ass.controllers.Industry industry) throws Exception {
		Industry indus = new Industry();
		indus.setIndustryName(industry.getIndusName());
		customerRepository.addIndustry(indus);
	}

	public List<Customer> findCustomerByIndustry(String industry) throws Exception {
		
		return customerRepository.searchCustomerByIndustry(industry);
	}

	public void editContact(Contact contact) throws Exception {
		customerRepository.editContact(contact);
		
	}

	public List<Customer> getCustomerByUser(User user) {
		return customerRepository.searchCustomerByUser(user);
	}

	public List<Customer> findCustomerByCombine(String name, String industry) throws Exception {
		
		return customerRepository.searchCustomerByCombine(name,industry);
	}

	public void editIndustry(Industry indus, String indusName) throws Exception {
		indus.setIndustryName(indusName);
		customerRepository.editIndustry(indus);
		
	}
	 
	 
}
