package com.orcunguducu.dynamic.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.validator.constraints.Email;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="USER")
public class User extends BaseDomain{
	@Email
	@Column(name="USER_NAME",unique=true,length = 100)
    private String username;
	
	@Column(name="FIRST_NAME", length = 50)
    private String firstName;
    
	@Column(name="LAST_NAME", length = 50)
    private String lastName;

	@NotNull
	@Size(min=5, max = 60)
    private String password;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy="user", fetch = FetchType.LAZY )     
    private List<UserQueryResult>  userQueryResults;
    
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(fetch = FetchType.LAZY, mappedBy="user")
    private List<Address> address;
    
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY ) 
    @JoinColumn(name = "ID",referencedColumnName="USER_ID",foreignKey = @ForeignKey(name = "SETTINGS_FK"))
    private UserSettings  userSettings;
    
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<UserQueryResult> getUserQueryResults() {
		return userQueryResults;
	}

	public void setUserQueryResults(List<UserQueryResult> userQueryResults) {
		this.userQueryResults = userQueryResults;
	}

	public List<Address> getAddress() {
		return address;
	}

	public void setAddress(List<Address> address) {
		this.address = address;
	}

	public UserSettings getUserSettings() {
		return userSettings;
	}

	public void setUserSettings(UserSettings userSettings) {
		this.userSettings = userSettings;
	}
}
