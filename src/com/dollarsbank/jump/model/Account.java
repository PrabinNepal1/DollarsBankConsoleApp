package com.dollarsbank.jump.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Account implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	private double balance;
	
	private ArrayList<Transaction> transactions;

	public Account(double balance) {
		super();
		this.balance = balance;
		this.transactions =  new ArrayList<Transaction> ();
	}

	public double getBalance() {
		return balance;
	}

	public ArrayList<Transaction> getTransactions() {
		return transactions;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public void setTransactions(Transaction transactions) {
		this.transactions.add(transactions);
	}
	
	

}
