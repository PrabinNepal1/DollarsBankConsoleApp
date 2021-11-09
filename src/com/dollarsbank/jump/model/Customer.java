package com.dollarsbank.jump.model;

import java.io.Serializable;

public class Customer implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String name, address, userId, password, phoneNo;
	
	private Account account;

	public Customer(String name, String address, String userId, String password, String phoneNo, Account account) {
		super();
		this.name = name;
		this.address = address;
		this.userId = userId;;
		this.password = password;
		this.phoneNo = phoneNo;
		this.account = account;
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
	
	public String getPhoneNo() {
		return phoneNo;
	}

	public Account getAccount() {
		return account;
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
	
	public void setPhoneNo(String phone) {
		this.phoneNo = phone;
	}

	public void setAccount(double depositAmount) {
		this.account = new Account(depositAmount);
	}
	
	
	
	
	
}
