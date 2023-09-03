package threads;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Discover implements Runnable {

	private ArrayList<String> section3;

	public Discover(ArrayList<String> section3) {
		this.section3 = section3;
	}

	public void run() {
		
		String display = display(section3);
		// Extract preliminary password for each sentence in section3 (password before truncation)
	    String preliminaryPassword = extractPreliminaryPassword(section3);
	    
	    //getting a valid 8 character truncated password 
	    String validPass = getValidPassword(preliminaryPassword);
	    System.out.println( display+  "- passwd is: " + validPass );
	}
	
	// Method to display section 3 for each record
		public String display(ArrayList<String> section3) {
			//display elements
			for(String s: section3) {
				return "Discover - Section 3 is: " +s;
			}
			return null;	
		}
	
	public static String extractPreliminaryPassword(ArrayList<String> section3) {
	    StringBuilder passwordBuilder = new StringBuilder();
	    String[] digitsAsWords = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
	    List<String> digitsAsWordsList = Arrays.asList(digitsAsWords);
	    for (String sentence : section3) {
	        String[] tokens = sentence.replaceAll(",", "").split("\\s+");
	        for (String token : tokens) {
	        	//checking if the character at the first position of a token is lowercase and is not a digit word
	            if (Character.isLowerCase(token.charAt(0)) && !digitsAsWordsList.contains(token)) {
	                char firstChar = token.charAt(0);
	                passwordBuilder.append(firstChar);    
	            } 
	            //if the token is a digit word i.e it is in the digitsasWordsList
	            else if (digitsAsWordsList.contains(token)) {
	                int digit = digitsAsWordsList.indexOf(token.toLowerCase());
	                passwordBuilder.append(String.valueOf(digit));
	            }  
	            else { 
	            	// ignore uppercase letters
	                continue;
	            }
	        }
	    }
	    String finalPass = passwordBuilder.toString();
	    return finalPass;
	}
	
	public static String getValidPassword(String preliminaryPassword) {
	    String password = preliminaryPassword.substring(0, 8); // take first 8 characters
	    String reversedPassword = new StringBuilder(preliminaryPassword.substring(preliminaryPassword.length()-8)).reverse().toString(); // take last 8 characters and reverse them
	    if (containsDigit(password)) {
	        return password + " -valid";
	    } else if (containsDigit(reversedPassword)) {
	        return reversedPassword + " -valid";
	    } else {
	        return password + " -invalid"; // return truncated password as invalid password
	    }
	}

	private static boolean containsDigit(String password) {
	    for (int i = 0; i < password.length(); i++) {
	        if (Character.isDigit(password.charAt(i))) {
	            return true;
	        }
	    }
	    return false;
	}

}
