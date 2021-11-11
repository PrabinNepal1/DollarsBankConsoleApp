package com.dollarsbank.jump.controller;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.dollarsbank.jump.model.Account;
import com.dollarsbank.jump.model.Customer;
import com.dollarsbank.jump.model.Transaction;
import com.dollarsbank.jump.model.Transaction.Type;
import com.dollarsbank.jump.utility.ColorsUtility;
import com.dollarsbank.jump.utility.DataGenerationStubUtility;
import com.dollarsbank.jump.utility.FileStorageUtility;

public class DollarsBankController {
	
	public List<Customer> customerList;
	public List<Account> accountList;
	public Customer currentCustomer , transferReceiver;
	
	
	
	public DollarsBankController(){
		
		customerList = new ArrayList<Customer>();
		
		
		File file = new File("resources/objectFile.txt");
		
		if(file.exists()) {
			customerList = FileStorageUtility.readFromFile(file);
		}
		else {
			
			for (int i=0; i < DataGenerationStubUtility.name.length; i++) {
				String name = DataGenerationStubUtility.name[i];
				String address = DataGenerationStubUtility.address[i];
				String customerId = DataGenerationStubUtility.userId[i];
				String password = DataGenerationStubUtility.password[i];
				String phoneNo = DataGenerationStubUtility.phoneNo[i];
				Double initialDeposit = DataGenerationStubUtility.initialDeposit[i];
				
				Account account = addInitialTransaction(initialDeposit, customerId);
				
				Customer customer = new Customer(name, address, customerId, password, phoneNo, account);

				customerList.add(customer);
				
			}
		}
		
	}
	
	public Account addInitialTransaction(double initialDeposit, String customerId) {
		
		Account account = new Account(initialDeposit);
		
		LocalDateTime timestamp = LocalDateTime.now();
		
		String depositDes = "Initial Deposit Amount In Account [" + customerId + "]\nBalance: "+ initialDeposit +" as on" + timestamp;
		
		Type transType =  Type.valueOf("DEPOSIT");
		
		Transaction transaction = new Transaction(depositDes, transType, timestamp);
		
		account.setTransactions(transaction);
		
		return account;
		
	}
	
	
	public boolean validUserId(String id) {
		for(int i=0; i <customerList.size(); i++) {
			if(customerList.get(i).getUserId().equals(id)) {
				return false;
			}
		}
		return true;
		
	}
	
	public boolean validPhoneNo(String phone) {
		
		if(phone.length() != 12) {
			return false;
		}
		
		Pattern number = Pattern.compile("^(\\d{3}[-]?){2}\\d{4}$");
		
		Matcher match = number.matcher(phone);
		
		return match.matches();
			
	}
	
	public boolean validPassword(String password) {
		
		boolean hasUpper = false;
		boolean hasLower = false; 
		boolean hasSpecial = false;
		
		if (password.length() < 8) {
			return false;
		}
		
		for(int i =0;i<password.length();i++) {
			if(Character.isUpperCase(password.charAt(i))){
				hasUpper=true;
			}
			if(Character.isLowerCase(password.charAt(i))){
				hasLower=true;
			}
		}
		
		Pattern special = Pattern.compile ("[!@#$%&*()_+=|<>?{}\\[\\]~-]");
	    Matcher m = special.matcher(password);
	    if(m.find()) {
	    	hasSpecial = true;
	    }
	    
	    if(hasUpper && hasLower && hasSpecial) {
	    	return true;
	    }
	    return false;
	}
	
	public void addCustomer(Customer customer) {
		customerList.add(customer);
	}
	
	public boolean checkLoginCreds(String userId, String password) {
			
		Optional<Customer> found = customerList.stream()
					.filter(c ->(c.getUserId().equals(userId) && c.getPassword().equals(password)))
					.findAny();
		
		if(found.isPresent()) {
			currentCustomer = found.get();
			return true;
		}
		
		return false;
			

	}
	
	public boolean deposit(double amt) {
		
		int index = customerList.indexOf(currentCustomer);
		
		double newBalance = currentCustomer.getAccount().getBalance()+amt;
		
		customerList.get(index).getAccount().setBalance(newBalance);
		
		LocalDateTime timestamp = LocalDateTime.now();
		
		String depositDes = "Deposit Amount In Account [" + currentCustomer.getUserId() + "]\nBalance: "+ newBalance +" as on" + timestamp;
		
		Type transType =  Type.valueOf("DEPOSIT");
		
		Transaction transaction = new Transaction(depositDes, transType, timestamp);
		
		customerList.get(index).getAccount().setTransactions(transaction);
		
		currentCustomer = customerList.get(index);
		
		return true;
		
	}
	
	public double currentBalance() {
		return currentCustomer.getAccount().getBalance();
	}
	
	
	public boolean withdraw(double amt) {
		
		int index = customerList.indexOf(currentCustomer);
		
		if (amt > customerList.get(index).getAccount().getBalance()) {
			return false;
		}
		
		double newBalance = currentCustomer.getAccount().getBalance() - amt;
		
		customerList.get(index).getAccount().setBalance(newBalance);
		
		LocalDateTime timestamp = LocalDateTime.now();
		
		String withdrawDes = "Withdrawn From Account [" + currentCustomer.getUserId() + "]\nBalance: "+ newBalance +" as on" + timestamp;
		
		Type transType =  Type.valueOf("WITHDRAW");
		
		Transaction transaction = new Transaction(withdrawDes, transType, timestamp);
		
		customerList.get(index).getAccount().setTransactions(transaction);
		
		currentCustomer = customerList.get(index);
		
		return true;
		
	}
	
	
	public boolean isValidReceiver(String userId) {
		
		Optional<Customer> receiverFound = customerList.stream()
				.filter(c -> c.getUserId().equals(userId)).findAny();
		
		if (receiverFound.isPresent() && (!userId.equals(currentCustomer.getUserId()))) {
			
			transferReceiver = receiverFound.get();
			return true;
		}
		
		return false;
	}
	
	public boolean transferFund(double amt){
		
		if (amt > currentCustomer.getAccount().getBalance()){
			return false;
		}
		
		int receiverIndex = customerList.indexOf(transferReceiver);
		int senderIndex = customerList.indexOf(currentCustomer);
		
		double customerBalance = currentCustomer.getAccount().getBalance()-amt;
		double receiverBalance = transferReceiver.getAccount().getBalance()+amt;
		
		customerList.get(senderIndex).getAccount().setBalance(customerBalance);
		customerList.get(receiverIndex).getAccount().setBalance(receiverBalance);
		
		LocalDateTime timestamp = LocalDateTime.now();
		
		String customerTransferDes = "Trasnferred From Account [" + currentCustomer.getUserId() + "]\nBalance: "+ customerBalance +" as on" + timestamp;
		String receiverTransferDes = "Trasnfer Received In Account [" + transferReceiver.getUserId() + "]\nBalance: "+ receiverBalance +" as on" + timestamp;
		
		
		Type transType =  Type.valueOf("TRANSFER");
		
		Transaction transaction = new Transaction(customerTransferDes, transType, timestamp);
		customerList.get(senderIndex).getAccount().setTransactions(transaction);
		
		transaction = new Transaction(receiverTransferDes, transType, timestamp);
		customerList.get(receiverIndex).getAccount().setTransactions(transaction);
		
		currentCustomer = customerList.get(senderIndex);
		transferReceiver = customerList.get(receiverIndex);
				
		return true;
			
	}	
	
	public void getCustomerInfo() {
		System.out.println(ColorsUtility.BLUE+ "+-----------------------+");
		System.out.println("| Customer Information: |");
		System.out.println("+-----------------------+" + ColorsUtility.RESET);
		System.out.println("UserID: " + currentCustomer.getUserId());
		System.out.println("Name: " + currentCustomer.getName());
		System.out.println("Address: " + currentCustomer.getAddress());
		System.out.println("Phone: " + currentCustomer.getPhoneNo());
		System.out.println("Available Balance: " + currentCustomer.getAccount().getBalance());
		
	}
	
	public void getRecentTransaction() {
		
		List <Transaction> transList = currentCustomer.getAccount().getTransactions();
		
		
		System.out.println(ColorsUtility.BLUE+ "+------------------------+");
		System.out.println("| 5 Recent Transactions: |");
		System.out.println("+------------------------+" + ColorsUtility.RESET);
		
		int transactionsCount = transList.size();
		
		
		for(int i=0; i < transactionsCount; i++) {
			
			System.out.println(i + ". " + transList.get(i).getDescription());
			
			if (i==4) {
				break;
			}
			
		}
	}
	
	public void writeToFile() {
		File file = new File("resources/objectFile.txt");
		
		FileStorageUtility.writeToFile(file, customerList);
	}

}
