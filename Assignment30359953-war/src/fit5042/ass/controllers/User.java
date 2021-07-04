package fit5042.ass.controllers;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@RequestScoped
@Named(value = "user")
public class User {
	private String userName;
	private String passwd;
	private String groups;
	public User(String userName, String passwd, String groups) {
		super();
		this.userName = userName;
		this.passwd = passwd;
		this.groups = groups;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getGroups() {
		return groups;
	}
	public void setGroups(String groups) {
		this.groups = groups;
	}
	
	
	
}
