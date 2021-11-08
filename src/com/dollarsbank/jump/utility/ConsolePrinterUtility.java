package com.dollarsbank.jump.utility;

public class ConsolePrinterUtility {
	
	public static void initialOutput() {
		System.out.println(ColorsUtility.BLUE+ "+---------------------------+");
		System.out.println("| DOLLARSBANK Welcomes You! |");
		System.out.println("+---------------------------+" + ColorsUtility.RESET);
		System.out.println("1. Create New Account");
		System.out.println("2. Login");
		System.out.println("3. Exit");
		System.out.println();
		System.out.println(ColorsUtility.GREEN + "Enter Choice (1, 2 or 3) :"+ ColorsUtility.RESET);
	}

}
