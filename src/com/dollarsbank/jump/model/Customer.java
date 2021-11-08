package com.dollarsbank.jump.model;

import java.io.Serializable;

public class Customer implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String name, address, userId, password;
	
	private double depositAmount;

	public Customer(String name, String address, String userId, String password, double depositAmount) {
		super();
		this.name = name;
		this.address = address;
		this.userId = userId;
		this.password = password;
		this.depositAmount = depositAmount;
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	public String getUserId() {
		return userId;
	}

	public String getPassword() {
		return password;
	}

	public double getDepositAmount() {
		return depositAmount;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setDepositAmount(double depositAmount) {
		this.depositAmount = depositAmount;
	}
	
	
	
	
	
}
