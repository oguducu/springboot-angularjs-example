package com.orcunguducu.dynamic.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="USER_SETTINGS")
public class UserSettings implements Serializable{
	
	@Id
	@Column(name="USER_ID")
	private long userId;
	
	@Column(name="INSERT_DATE", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
	private Date insertDate;
	
	@NotNull
	@Column(name="HOME_PAGE_WIDGETS",length = 500)
    private String homePageWidgets;
	
    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL, mappedBy="userSettings", fetch = FetchType.LAZY )     
    private User user;

	public String getHomePageWidgets() {
		return homePageWidgets;
	}

	public void setHomePageWidgets(String homePageWidgets) {
		this.homePageWidgets = homePageWidgets;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public Date getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}
}
