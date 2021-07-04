package fit5042.ass.repository;

import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import fit5042.ass.repository.entities.Contact;
import fit5042.ass.repository.entities.Customer;
import fit5042.ass.repository.entities.Industry;
import fit5042.ass.repository.entities.User;

@Stateless
public class JPACustomerRepository implements CustomerRepository {
	@PersistenceContext(unitName = "Assignment30359953-ejb")
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public void addCustomer(Customer customer) throws Exception {
		List<Customer> customers = entityManager.createNamedQuery(Customer.GET_ALL).getResultList();
		int id;
		if (customers.size() == 0)
			id = 1;
		else
			id = customers.get(0).getCusId() + 1;
		customer.setCusId(id);
		
		entityManager.persist(customer);
	}

	@Override
	public void addContact(Contact contact) throws Exception {
		// TODO Auto-generated method stub
		List<Contact> contacts = entityManager.createNamedQuery(Contact.GET_ALL).getResultList();
		int id;
		if (contacts.size() == 0)
			id = 1;
		else
			id = contacts.get(0).getConId() + 1;

		
		contact.setConId(id);
		entityManager.persist(contact);
	}
	
	
	@Override
	public Customer searchCustomerById(int id) throws Exception {
		// TODO Auto-generated method stub
		Customer customer = entityManager.find(Customer.class,id);
		
		return customer;
		
	}

	@Override
	public List<Customer> searchCustomerByIndustry(String industry) throws Exception {
		
		Industry indus = searchIndustryByName(industry).get(0);
		
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		// specify the type i.e. class
		CriteriaQuery<Customer> cQuery = builder.createQuery(Customer.class);
		// specify the root? Meaning?
		Root<Customer> u = cQuery.from(Customer.class);
		try {
			Predicate predicate = builder.equal(u.get("industry").as(Industry.class),indus);
			cQuery.where(predicate);
			TypedQuery<Customer> tQuery = entityManager.createQuery(cQuery);
			List<Customer> industrys = tQuery.getResultList();
			return industrys;
		}
		/*Predicate predicate = builder.equal(u.get("industry").as(Industry.class),indus);
		cQuery.where(predicate);
		TypedQuery<Customer> tQuery = entityManager.createQuery(cQuery);
		List<Customer> industrys = tQuery.getResultList();
		return industrys;*/
		catch(Exception ex) {
			List<Customer> industrys = null;
			return industrys;
		}
		
	}

	@Override
	public Contact searchContactById(int id) throws Exception {
		Contact contact = entityManager.find(Contact.class,id);
		
		return contact;
	}

	@Override
	public void deleteCustomerById(int id) throws Exception {
		// TODO Auto-generated method stub
		Customer customer = this.searchCustomerById(id);
		if (customer != null) {
			entityManager.remove(customer);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Delete succesfully"));
		}

	}

	@Override
	public void deleteContactById(int id) throws Exception {
		Contact contact = this.searchContactById(id);
		if (contact != null) {
			entityManager.remove(contact);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Delete succesfully"));
		}

	}

	@Override
	public void editCustomer(Customer customer) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void editContact(Contact contact) throws Exception {
		entityManager.merge(contact);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Customer> getAllCustomer() throws Exception {
		// TODO Auto-generated method stub
		return entityManager.createNamedQuery(Customer.GET_ALL).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Contact> getAllContact() throws Exception {
		// TODO Auto-generated method stub
		return entityManager.createNamedQuery(Contact.GET_ALL).getResultList();
	}

	@Override
	public Customer getCusRef(int id) {
		return entityManager.getReference(Customer.class, id);
	}

	@Override
	public Contact getConRef(int id) {
		return entityManager.getReference(Contact.class, id);
	}

	@Override
	public void addUser(User user) throws Exception {
		entityManager.persist(user);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Add succesfully"));
	}

	@Override
	public User findUserById(int id) throws Exception {
		// TODO Auto-generated method stub
		User user = entityManager.find(User.class, id);
		return user;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAllUsers() throws Exception {
		return entityManager.createNamedQuery(User.GET_ALL_QUERY_NAME).getResultList();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<User> findUserByName(String name) throws Exception {
		// create builder first
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		// specify the type i.e. class
		CriteriaQuery cQuery = builder.createQuery(User.class);
		// specify the root? Meaning?
		Root<User> u = cQuery.from(User.class);
		Predicate predicate = builder.equal(u.get("userName").as(String.class),name);
		cQuery.where(predicate);
		TypedQuery tQuery = entityManager.createQuery(cQuery);
		List<User> users = tQuery.getResultList();
		return users;
	}

	@Override
	public void updateCustomer(Customer customer) {
		entityManager.merge(customer);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Customer has been updated"));
	}

	@Override
	public void deleteUserById(int userId) throws Exception {
		// TODO Auto-generated method stub
		User user = this.findUserById(userId);
		if (user != null) {
			entityManager.remove(user);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Delete succesfully"));
		}
	}

	@Override
	public void updateUser(User user) throws Exception {
		// TODO Auto-generated method stub
		entityManager.merge(user);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("user has been updated"));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Industry> getAllIndustry() throws Exception {
		// TODO Auto-generated method stub
		return entityManager.createNamedQuery(Industry.GET_ALL).getResultList();
	}

	@Override
	public List<Customer> searchCustomerByName(String cusName) throws Exception {
		// create builder first
			CriteriaBuilder builder = entityManager.getCriteriaBuilder();
			// specify the type i.e. class
			CriteriaQuery<Customer> cQuery = builder.createQuery(Customer.class);
			// specify the root? Meaning?
			Root<Customer> c = cQuery.from(Customer.class);
			Predicate predicate = builder.equal(c.get("companyName").as(String.class),cusName);
			cQuery.where(predicate);
			TypedQuery<Customer> tQuery = entityManager.createQuery(cQuery);
			List<Customer> customers = tQuery.getResultList();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Customer has been found"));
			return customers;
	}

	@Override
	public List<Industry> searchIndustryByName(String indusName) throws Exception {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		// specify the type i.e. class
		CriteriaQuery<Industry> cQuery = builder.createQuery(Industry.class);
		// specify the root? Meaning?
		Root<Industry> c = cQuery.from(Industry.class);
		Predicate predicate = builder.equal(c.get("industryName").as(String.class),indusName);
		cQuery.where(predicate);
		TypedQuery<Industry> tQuery = entityManager.createQuery(cQuery);
		List<Industry> industry = tQuery.getResultList();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Industry has been found"));
		return industry;
	}

	@Override
	public void deleteIndustryById(int id) throws Exception {
		Industry industry = this.searchIndustryById(id);
		boolean flag = true;
		for (fit5042.ass.repository.entities.Customer cus : getAllCustomer()) {
			if (cus.getIndustry().getInsId() == id) {
				flag = false;
			}
		}
		if (flag) {
			entityManager.remove(industry);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Delete succesfully"));
		}
		else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Industry has customers, please check again"));
		}
		/*if (industry != null) {
			entityManager.remove(industry);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Delete succesfully"));*/
		
	}



	@Override
	public Industry searchIndustryById(int id) throws Exception {
		Industry industry = entityManager.find(Industry.class,id);
		return industry;
	}

	@Override
	public void addIndustry(Industry industry) throws Exception {
		entityManager.persist(industry);
		
	}

	@Override
	public List<Customer> searchCustomerByUser(User user) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		// specify the type i.e. class
		CriteriaQuery<Customer> cQuery = builder.createQuery(Customer.class);
		// specify the root? Meaning?
		Root<Customer> u = cQuery.from(Customer.class);
		try {
			Predicate predicate = builder.equal(u.get("managedBy").as(User.class),user);
			cQuery.where(predicate);
			TypedQuery<Customer> tQuery = entityManager.createQuery(cQuery);
			List<Customer> customers = tQuery.getResultList();
			return customers;
		}
		/*Predicate predicate = builder.equal(u.get("industry").as(Industry.class),indus);
		cQuery.where(predicate);
		TypedQuery<Customer> tQuery = entityManager.createQuery(cQuery);
		List<Customer> industrys = tQuery.getResultList();
		return industrys;*/
		catch(Exception ex) {
			List<Customer> customers = null;
			return customers;
		}
	}

	@Override
	public List<Customer> searchCustomerByCombine(String name, String industry) throws Exception {
		Industry indus = searchIndustryByName(industry).get(0);
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Customer> criteriaQuery = criteriaBuilder.createQuery(Customer.class);
		Root<Customer> c = criteriaQuery.from(Customer.class);
		Predicate predicateName
		  = criteriaBuilder.like(c.get("companyName"), ("%"+name+"%"));
		Predicate predicateIndus
		  = criteriaBuilder.equal(c.get("industry"), (indus));
		Predicate predicateForTwo
		  = criteriaBuilder.and(predicateName, predicateIndus);
		criteriaQuery.where(predicateForTwo);
		List<Customer> customers = entityManager.createQuery(criteriaQuery).getResultList();
        return customers;
	}

	@Override
	public void editIndustry(Industry indus) throws Exception {
		entityManager.merge(indus);
		
	}
	}
