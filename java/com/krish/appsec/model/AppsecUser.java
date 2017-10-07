package com.krish.appsec.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.validation.annotation.Validated;

@Entity
@Validated
public class AppsecUser { 
	@Id
	private String userId;
	@NotNull
	@Size(min=2, max=20)	   
	private String firstName;
	@Size(min=2, max=20)	   
	private String lastName;
	@Size(min=2, max=20)	   
	private String city;
	@Size(min=2, max=5)	   
	private String zipCode;
	private Double balanceAmount;
	
	public AppsecUser(){}
	
	public AppsecUser(String userId, String firstName, String lastName, String city,
			String zipCode, Double balanceAmount) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.city = city;
		this.zipCode = zipCode;
		this.balanceAmount = balanceAmount;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
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
	
	public Double getBalanceAmount() {
		return balanceAmount;
	}

	public void setBalanceAmount(Double balanceAmount) {
		this.balanceAmount = balanceAmount;
	}

	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}	
	
	} 
