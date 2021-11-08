package com.dollarsbank.jump.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Transaction implements Serializable {


	private static final long serialVersionUID = 1L;
	
	private String userId;
	private String description;
	private String transactionType;
	private LocalDateTime ldt = LocalDateTime.now();
	
	public Transaction(String userId, String description, String transactionType, LocalDateTime ldt) {
		super();
		this.userId = userId;
		this.description = description;
		this.transactionType = transactionType;
		this.ldt = ldt;
	}

	public String getUserId() {
		return userId;
	}

	public String getDescription() {
		return description;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public LocalDateTime getLdt() {
		return ldt;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public void setLdt(LocalDateTime ldt) {
		this.ldt = ldt;
	}
	

}
