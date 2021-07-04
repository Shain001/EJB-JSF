package fit5042.ass.repository.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.ws.rs.Path;


@Entity
@NamedQueries({
    @NamedQuery(name = Customer.GET_ALL, query = "SELECT c FROM Customer c order by c.cusId desc")})
public class Customer implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String GET_ALL = "Customer.getAll";
	@NotNull(message = "industry cannot be null")
	private Industry industry;	
	@Size(min = 3, max = 4, message = "invalid postcode")
	private String postCode;
	private String country;
	private String state;
	private String street;
	@NotNull(message = "company name cannot be null")
	private String companyName;
	private int cusId;
	private User managedBy;
	private Set<Contact> contacts;
	
	@Temporal(TemporalType.DATE)
	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	private Date registerDate;
	
	
	public Customer(Industry industry, String postCode, String country, String state, String street, String companyName,
			int cusId, User managedBy, Set<Contact> contacts, Date reDate) {
		super();
		this.industry = industry;
		this.postCode = postCode;
		this.country = country;
		this.state = state;
		this.street = street;
		this.companyName = companyName;
		this.cusId = cusId;
		this.managedBy = managedBy;
		this.contacts = new HashSet<Contact>();
		this.contacts = contacts;
		this.registerDate = reDate;
	}
	
	public Customer() {
		super();
		contacts = new HashSet<>();
	}
	
	@OneToOne
	public Industry getIndustry() {
		return industry;
	}

	public void setIndustry(Industry indus) {
		this.industry = indus;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	public int getCusId() {
		return cusId;
	}

	public void setCusId(int cusId) {
		this.cusId = cusId;
	}
	
	@ManyToOne
	public User getManagedBy() {
		return managedBy;
	}

	public void setManagedBy(User managedBy) {
		this.managedBy = managedBy;
	}
	
	@OneToMany(mappedBy = "company",cascade = {CascadeType.REMOVE},orphanRemoval = true)
	public Set<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(Set<Contact> contacts) {
		this.contacts = contacts;
	}
	
	public void addContact(Contact contact) {
		contacts.add(contact);
	}
	
}
