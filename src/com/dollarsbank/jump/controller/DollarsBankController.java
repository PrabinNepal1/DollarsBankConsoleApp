package com.dollarsbank.jump.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.dollarsbank.jump.model.Account;
import com.dollarsbank.jump.model.Customer;
import com.dollarsbank.jump.utility.DataGenerationStubUtility;

public class DollarsBankController {
	
	public List<Customer> customerList;
	public List<Account> accountList;
	
	
	public DollarsBankController(){
		
		customerList = new ArrayList<Customer>();
		
		for (int i=0; i < DataGenerationStubUtility.name.length; i++) {
			String name = DataGenerationStubUtility.name[i];
			String address = DataGenerationStubUtility.address[i];
			String customerId = DataGenerationStubUtility.userId[i];
			String password = DataGenerationStubUtility.password[i];
			String phoneNo = DataGenerationStubUtility.phoneNo[i];
			Double initialDeposit = DataGenerationStubUtility.initialDeposit[i];
			
			Account account = new Account(initialDeposit);
			
			Customer customer = new Customer(name, address, customerId, password, phoneNo, account);

			customerList.add(customer);
			
		}
		
	}
	
	public boolean checkUserId(String id) {
		for(int i=0; i <customerList.size(); i++) {
			if(customerList.get(i).getUserId().equals(id)) {
				return true;
			}
		}
		return false;
		
	}
	
	public boolean validPhoneNo(String phone) {
		
		if(phone.length() != 13) {
			return false;
		}
		
		Pattern number = Pattern.compile("?([0-9]{3})?[-. ]?([0-9]{3})[-. ]?([0-9]{4}");
		
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
	
	public boolean checkLoginCred(String userId, String password) {
		
		boolean id = customerList.stream().anyMatch(c -> c.getUserId().equals(userId));
		boolean pass = customerList.stream().anyMatch(c -> c.getPassword().equals(password));
		
		if (id && pass) {
			return true;
		}
		
		return false;
	}
}
