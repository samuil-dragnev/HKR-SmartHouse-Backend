package com.hkr.smarthouse.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
@Entity
@Table(name = "unauthusers")
@XmlRootElement
public class UnauthorizedUser implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "userSSN", unique = true, nullable = false)
	private String ssn;
	@Column(name = "userPassword", nullable = false)
	private String password;
	@Column(name = "userFirstName", nullable = false)
	private String first_name;
	@Column(name = "userLastName", nullable = false)
	private String last_name;
	@Column(name = "userEmail", nullable = false)
	private String email;
	
	@Column(name = "userIsAdmin", nullable = false)
	private boolean isAdmin;
	
	public UnauthorizedUser () {}
	
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
	public String getEmail() { return email; }
	public void setEmail(String email) { this.email = email; }
}
