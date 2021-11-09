package com.dollarsbank.jump.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Transaction implements Serializable {


	private static final long serialVersionUID = 1L;
	
	public enum Type {WITHDRAWL, DEPOSIT, INITIAL_DEPOSIT, TRANSFER}
	
	private String description;
	private Type transactionType;
	private LocalDateTime ldt = LocalDateTime.now();
	
	public Transaction(String description, Type transactionType, LocalDateTime ldt) {
		super();
		this.description = description;
		this.transactionType = transactionType;
		this.ldt = ldt;
	}


	public String getDescription() {
		return description;
	}

	public Type getTransactionType() {
		return transactionType;
	}

	public LocalDateTime getLdt() {
		return ldt;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setTransactionType(Type transactionType) {
		this.transactionType = transactionType;
	}

	public void setLdt(LocalDateTime ldt) {
		this.ldt = ldt;
	}
	

}
