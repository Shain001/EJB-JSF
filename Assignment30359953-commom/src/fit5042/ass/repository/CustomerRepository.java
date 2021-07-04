package fit5042.ass.repository;

import java.util.List;
import java.util.Set;

import fit5042.ass.repository.entities.Contact;
import fit5042.ass.repository.entities.Customer;
import fit5042.ass.repository.entities.Industry;
import fit5042.ass.repository.entities.User;


public interface CustomerRepository {
	/**
     * Add the customer being passed as parameter into the repository
     *
     * @param customer - the customer to add
	 * @return 
     */
    public void addCustomer(Customer customer) throws Exception;
		// TODO Auto-generated method stu
    
    /**
     * Add the contact being passed as parameter into the repository
     *
     * @param contact - the contact to add
     * @return 
     */
    public void addContact(Contact contact) throws Exception;
    
    /**
     * Add new user(manager)
     */
    public void addUser(User user) throws Exception;
    
    public void addIndustry(Industry industry) throws Exception;
    
    /**
     * find user by id
     */
    public User findUserById(int id) throws Exception;
    
    /**
     * find user by name
     */
    public List<User> findUserByName(String Name) throws Exception;
    
    /**
     * get all users
     */
    public List<User> getAllUsers() throws Exception;
    public List<Industry> getAllIndustry() throws Exception;
    
    public Customer getCusRef(int id) throws Exception;
	
	public Contact getConRef(int id) throws Exception;
    /**
     * Search for a Customer by its property ID
     *
     * @param id - the customerId of the Customer to search for
     * @return the Customer found
     */
    public Customer searchCustomerById(int id) throws Exception;
    
    /**
     * Search for a Customer by its industry
     *
     * @param industry - the industry of the Customer to search for
     * @return the Customer found
     */
    public List<Customer> searchCustomerByIndustry(String industry) throws Exception;
    
    public List<Customer> searchCustomerByName(String cusName) throws Exception;
    public List<Industry> searchIndustryByName(String indusName) throws Exception;
    public Industry searchIndustryById(int id) throws Exception;
    
    /**
     * Search for a Contact by its Contact ID
     *
     * @param id - the contactId of the Contact to search for
     * @return the Contact found
     */
    public Contact searchContactById(int id) throws Exception;

    /**
     * Search for a Customer by its Customer ID
     *
     * @param id - the CustomerId of the Customer to delete
     */
    public void deleteCustomerById(int id) throws Exception;
    
    /**
     * Search for a Contact by its property ID
     *
     * @param id - the contactId of the Customer to delete
     */
    public void deleteContactById(int id) throws Exception;
    
    public void deleteIndustryById(int id) throws Exception;
    
    /**
     * Update a customer in the repository
     *
     * @param customer - the updated information regarding a customer
     */
    public void editCustomer(Customer customer) throws Exception;
    
    /**
     * Update a contact in the repository
     *
     * @param contact - the updated information regarding a contact
     */
    public void editContact(Contact contact) throws Exception;
    
    /**
     * Return all the Customer in the repository
     *
     * @return all the Customer in the repository
     */
    public List<Customer> getAllCustomer() throws Exception;
    

    /**
     * Return all the contact in the repository
     *
     * @return all the contact in the repository
     */
    public List<Contact> getAllContact() throws Exception;


	public void updateCustomer(Customer customer) throws Exception;
	public void updateUser(User user) throws Exception;

	public void deleteUserById(int userId)throws Exception;

	public List<Customer> searchCustomerByUser(User user);

	public List<Customer> searchCustomerByCombine(String name, String industry) throws Exception;

	public void editIndustry(Industry indus) throws Exception;
	 
	
	
}
