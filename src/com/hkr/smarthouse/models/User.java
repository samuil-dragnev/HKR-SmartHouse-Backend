package com.hkr.smarthouse.models;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "Users")
@XmlRootElement
public class User {
	@Id
	@Column(name = "userSSN", unique = true, nullable = false)
	private String ssn;
	@Column(name = "userPassword", nullable = false)
	private String password;
	@Column(name = "userFirstName", nullable = false)
	private String first_name;
	@Column(name = "userLastName", nullable = false)
	private String last_name;
	@Column(name = "userIsAdmin", nullable = false)
	private boolean isAdmin;
	@Column(nullable = false)
	private boolean root;
	@Column(nullable = false)
	private boolean isOnline;
	
	public String getSsn() { return ssn; }
	public void setSsn(String ssn) { this.ssn = ssn; }
	public String getPassword() { return password; }
	public void setPassword(String password) { this.password = password; }
	public String getFirst_name() { return first_name; }
	public void setFirst_name(String first_name) { this.first_name = first_name; }
	public String getLast_name() { return last_name; }
	public void setLast_name(String last_name) { this.last_name = last_name; }
	public boolean isAdmin() { return isAdmin; }
	public void setAdmin(boolean isAdmin) { this.isAdmin = isAdmin; }
	public boolean isOnline() { return isOnline; }
	public void setOnline(boolean isOnline) { this.isOnline = isOnline; }
	public boolean isRoot() { return root; }
	public void setRoot(boolean root) { this.root = root; }
}
