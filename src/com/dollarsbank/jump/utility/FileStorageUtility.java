package com.dollarsbank.jump.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import com.dollarsbank.jump.model.Customer;

public class FileStorageUtility {
	
	public static List<Customer> readFromFile(File filePath){
		
		try {
			 
            FileInputStream fileIn = new FileInputStream(filePath);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
 
            List<Customer> customerList = (List<Customer>) objectIn.readObject();
 
            objectIn.close();
            
            return customerList;
            
        } catch (FileNotFoundException ex) {
        	ex.getMessage();
            
        } catch (IOException ex) {
        	ex.getMessage();
        }
		catch (ClassNotFoundException ex) {
			ex.getMessage();
		}
		catch (Exception ex) {
            ex.getMessage();
        }
		
		return null;
		
	}
	
public static List<Customer> writeToFile(File filePath, List<Customer> customerList){
		
		try {
			 
            FileOutputStream fileIn = new FileOutputStream(filePath);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileIn);
 
            objectOut.writeObject(customerList);
 
            objectOut.close();
            
            return customerList;
            
        } catch (FileNotFoundException ex) {
        	ex.getMessage();
            
        } catch (IOException ex) {
        	ex.getMessage();
        }
		catch (Exception ex) {
            ex.getMessage();
        }
		
		return null;
		
	}

}
