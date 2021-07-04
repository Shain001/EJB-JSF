package fit5042.ass.repository.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity
@NamedQueries({
    @NamedQuery(name = Industry.GET_ALL, query = "SELECT i FROM Industry i order by i.insId desc")})
public class Industry implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String GET_ALL = "Industry.getAll";
	private int insId;
	@NotNull
	private String industryName;
	
	public Industry(int insId, String industryName) {
		super();
		this.insId = insId;
		this.industryName = industryName;
	}
	
	public Industry() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@OneToMany(cascade = CascadeType.REMOVE)
	public int getInsId() {
		return insId;
	}
	public void setInsId(int insId) {
		this.insId = insId;
	}
	public String getIndustryName() {
		return industryName;
	}
	public void setIndustryName(String industryName) {
		this.industryName = industryName;
	}
	
}
