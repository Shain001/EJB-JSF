package fit5042.ass.repository.entities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "USERS")
@NamedQueries({
    @NamedQuery(name = User.GET_ALL_QUERY_NAME, query = "SELECT u FROM User u order by u.userId desc")})
public class User {
	
	public static final String GET_ALL_QUERY_NAME = "User.getAll";
	
	private String userName;
	private String passWord;
	private int userId;
	private Set<Customer> customers;
	private String groups;
	
	public User(String userName, String passWord, int id, Set<Customer> customers,String group) {
		super();
		this.userName = userName;
		this.passWord = passWord;
		this.userId = id;
		this.customers = customers;
		this.groups = group;
	}
	@Column(name = "GROUPS")
	public String getGroup() {
		return groups;
	}

	public void setGroup(String group) {
		this.groups = group;
	}

	public User() {
		super();
		customers = new HashSet<>();
		
	}
	

	@Column(name = "USERNAME")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
	public int getUserId() {
		return userId;
	}

	public void setUserId(int id) {
		this.userId = id;
	}
	
	@OneToMany(mappedBy = "managedBy",cascade = CascadeType.MERGE)
	public Set<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(Set<Customer> customers) {
		this.customers = customers;
	}
	
	public void addCustomer(Customer customer) {
		customers.add(customer);
	}
	
	public ArrayList<String> getManagedCustomers() {
		ArrayList<String> cusId = new ArrayList<String>();
		for (Customer cus : customers) {
			cusId.add(Integer.toString(cus.getCusId()) + " : " + cus.getCompanyName());
		}
		return cusId;
	}
	
}
