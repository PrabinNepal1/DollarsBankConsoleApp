package com.dollarsbank.jump.application;
 
import java.util.Scanner;

import com.dollarsbank.jump.controller.DollarsBankController;
import com.dollarsbank.jump.model.Account;
import com.dollarsbank.jump.model.Customer;
import com.dollarsbank.jump.utility.ColorsUtility;
import com.dollarsbank.jump.utility.ConsolePrinterUtility;

public class DollarsBankApplication {

	public static void main(String[] args) {
		
		DollarsBankController controller = new DollarsBankController();
		
		
		Scanner scan = new Scanner(System.in);
		
		boolean appUser = true;
		
		while(appUser) {
			
			ConsolePrinterUtility.initialOutput();
			
				
				int choice = Integer.parseInt(scan.nextLine());
				if(choice ==1) {
					ConsolePrinterUtility.registrationOutput(1);
					System.out.print(ColorsUtility.GREEN);
					String customerName = scan.nextLine();
					System.out.print(ColorsUtility.RESET);
					ConsolePrinterUtility.registrationOutput(2);
					System.out.print(ColorsUtility.GREEN);
					String customerAddress = scan.nextLine();
					System.out.print(ColorsUtility.RESET);
					String customerPhoneNo = "";
					while(true) {
						
						ConsolePrinterUtility.registrationOutput(3);
						System.out.print(ColorsUtility.GREEN);
						customerPhoneNo = scan.nextLine();
						
						if(controller.validPhoneNo(customerPhoneNo)) {
							System.out.print(ColorsUtility.RESET);
							break;
						}
						else {
							System.out.print(ColorsUtility.RESET);
							ConsolePrinterUtility.errorOutput(6);
						}
					}
					
					String customerUserId = "";
					while(true) {
						
						ConsolePrinterUtility.registrationOutput(4);
						System.out.print(ColorsUtility.GREEN);
						customerUserId = scan.nextLine();
						
						if(controller.validUserId(customerUserId)) {
							System.out.print(ColorsUtility.RESET);
							break;
						}
						else {
							System.out.print(ColorsUtility.RESET);
							ConsolePrinterUtility.errorOutput(5);
						}
					}
					
					String customerPassword = "";
					while(true) {
						
						ConsolePrinterUtility.registrationOutput(5);
						System.out.print(ColorsUtility.GREEN);
						customerPassword = scan.nextLine();
						
						if(controller.validUserId(customerPassword)) {
							System.out.print(ColorsUtility.RESET);
							break;
						}
						else {
							System.out.print(ColorsUtility.RESET);
							ConsolePrinterUtility.errorOutput(2);
						}
					}
					
					double initialDeposit = 0;
					while(initialDeposit <= 0) {

						ConsolePrinterUtility.registrationOutput(6);
						System.out.print(ColorsUtility.GREEN);
						initialDeposit = Double.parseDouble(scan.nextLine());
						
						if (initialDeposit > 0) {
							break;
						}
						else {
							System.out.print(ColorsUtility.RESET);
							ConsolePrinterUtility.errorOutput(3);
						}
					}
					
					Account account = controller.addInitialTransaction(initialDeposit, customerPassword);
					Customer customer = new Customer(customerName, customerAddress, customerUserId, customerPassword, customerPhoneNo, account);
					
					controller.addCustomer(customer);
					
					System.out.print(ColorsUtility.RESET);
					ConsolePrinterUtility.registrationOutput(7);
					
				}
				else if(choice==2) {
					boolean logInPrompt = true;
					while(logInPrompt) {
						ConsolePrinterUtility.loginOutput(1);
						System.out.print(ColorsUtility.GREEN);
						String customerUserId = scan.nextLine();
						System.out.print(ColorsUtility.RESET);
						ConsolePrinterUtility.loginOutput(2);
						System.out.print(ColorsUtility.GREEN);
						String customerPassword = scan.nextLine();
						System.out.print(ColorsUtility.RESET);
						
						if(controller.checkLoginCreds(customerUserId, customerPassword)) {
							boolean loggedIn = true;
							while(loggedIn) {
								ConsolePrinterUtility.validUserOutput();
						
								int userChoice = Integer.parseInt(scan.nextLine());
								
								if(userChoice == 1) {
									while(true) {
										ConsolePrinterUtility.depositOutput(1);
										System.out.print(ColorsUtility.GREEN);
										double depositAmount = Double.parseDouble(scan.nextLine());
										if(depositAmount > 0) {
											if(controller.deposit(depositAmount)) {
												System.out.print(ColorsUtility.RESET);
												ConsolePrinterUtility.depositOutput(2);
												System.out.println(controller.currentBalance());
												break;
											}
											else {
												System.out.print(ColorsUtility.RESET);
												ConsolePrinterUtility.depositOutput(3);
											}
										}
										else {
											System.out.print(ColorsUtility.RESET);
											ConsolePrinterUtility.depositOutput(4);
											
										}
										
										
									}
								}
								
								else if(userChoice == 2) {
									while(true) {
										ConsolePrinterUtility.withdrawOutput(1);	
										System.out.print(ColorsUtility.GREEN);
										double withdrawAmount = Double.parseDouble(scan.nextLine());
										if(withdrawAmount > 0) {
												if(controller.withdraw(withdrawAmount)) {
												System.out.print(ColorsUtility.RESET);
												ConsolePrinterUtility.withdrawOutput(2);
												System.out.println(controller.currentBalance());
												break;
											}else {
													System.out.print(ColorsUtility.RESET);
													ConsolePrinterUtility.withdrawOutput(3);
												}
											
										}else {
											System.out.print(ColorsUtility.RESET);
											ConsolePrinterUtility.withdrawOutput(4);
											
										}
										
									}
								}
								
								else if(userChoice == 3) {
									while(true) {
										String receiverUserId = "";
										while(true) {
											
											ConsolePrinterUtility.transferOutput(1);
											System.out.print(ColorsUtility.GREEN);
											receiverUserId = scan.nextLine();
											
											if(controller.isValidReceiver(receiverUserId)) {
												System.out.print(ColorsUtility.RESET);
												break;
											}
											else {
												System.out.print(ColorsUtility.RESET);
												ConsolePrinterUtility.transferOutput(3);
											}
											
										}
										
										
										double transferAmount = 0;
										while(true) {
											ConsolePrinterUtility.transferOutput(2);
											System.out.print(ColorsUtility.GREEN);
											transferAmount = Double.parseDouble(scan.nextLine());
											
											if(transferAmount > 0) {
												System.out.print(ColorsUtility.RESET);
												break;
											}
											else {
												System.out.print(ColorsUtility.RESET);
												ConsolePrinterUtility.transferOutput(6);
											}
										}
										
										if(controller.transferFund(transferAmount)) {
												System.out.print(ColorsUtility.RESET);
												ConsolePrinterUtility.transferOutput(4);
												System.out.println(controller.currentBalance());
												break;
										}
										else {
											System.out.print(ColorsUtility.RESET);
											ConsolePrinterUtility.transferOutput(7);
										}
										
									
							
									}
								}
								
								else if(userChoice == 4) {
									
									controller.getRecentTransaction();
									
								}
								
								else if(userChoice == 5) {
									
									controller.getCustomerInfo();
								
								}
								
								else if(userChoice == 6) {
									
									loggedIn = false;
									logInPrompt = false;
									System.out.println("You have signed out!");
								}
								
								else {
									ConsolePrinterUtility.errorOutput(1);
								}
								
							}
						}
						else {
							System.out.print(ColorsUtility.RESET);
							ConsolePrinterUtility.errorOutput(4);
						}
					}
					
				}
				else if(choice==3) {
					appUser = false;
					System.out.print(ColorsUtility.BLUE);
					System.out.println("Thank you for using DollarsBank Application!");
				}
				
				else {
					
					ConsolePrinterUtility.errorOutput(4);
					
				}
			
		}
		
		scan.close();
		
	}

}
