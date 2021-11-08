package com.dollarsbank.jump.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Account implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	private String userId, password;
	
	private double balance;
	
	private ArrayList<Transaction> transactions;

	public Account(String userId, String password, double balance) {
		super();
		this.userId = userId;
		this.password = password;
		this.balance = balance;
		this.transactions =  new ArrayList<Transaction> ();
	}

	public String getUserId() {
		return userId;
	}

	public String getPassword() {
		return password;
	}

	public double getBalance() {
		return balance;
	}

	public ArrayList<Transaction> getTransactions() {
		return transactions;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public void setTransactions(Transaction transactions) {
		this.transactions.add(transactions);
	}
	
	

}
