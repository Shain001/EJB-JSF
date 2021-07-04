package fit5042.ass.repository.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@NamedQueries({
    @NamedQuery(name = Contact.GET_ALL, query = "SELECT c FROM Contact c order by c.conId desc")})
public class Contact {
	public static final String GET_ALL = "Contact.getAll";
	private String phone;
	private String title;
	private String firstName;
	private String lastName;
	@NotNull
	private Customer company;
	private int conId;
	@Pattern(regexp = "[\\w\\.-]*[a-zA-Z0-9_]@[\\w\\.-]*[a-zA-Z0-9]\\.[a-zA-Z][a-zA-Z\\.]*[a-zA-Z]")
	private String email;
	
	public Contact(String phone, String title, String firstName, String lastName, Customer cumtomer, int conId,String email) {
		this.phone = phone;
		this.title = title;
		this.firstName = firstName;
		this.lastName = lastName;
		if(cumtomer == null)
				this.company = null;
		else
			this.company = cumtomer;
		this.conId = conId;
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Contact() {
		this.phone = "0000000000";
		this.title = null;
		this.firstName = null;
		this.lastName = null;
		this.company = null;
		this.email = null;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	public int getConId() {
		return conId;
	}

	public void setConId(int conId) {
		this.conId = conId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	@ManyToOne
	public Customer getCompany() {
		return company;
	}

	public void setCompany(Customer cumtomer) {
		this.company = cumtomer;
	}
}
