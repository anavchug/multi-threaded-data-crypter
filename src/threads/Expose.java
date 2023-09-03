package threads;

import java.util.ArrayList;

class Expose implements Runnable {

	private ArrayList<String> section2;
	private ArrayList<String> part1List;
	private ArrayList<Integer> part2List;

	public Expose(ArrayList<String> section2) {
		this.section2 = section2;
	}
	@Override
	public void run() {
		String display = display(section2);
		splitMessage(section2);
		if(checkConditions(part1List, part2List)) {
			String finalMessage = CPrime(part1List, part2List);
			System.out.println(display + "- Deciphered code is: " + finalMessage + "- Valid");
		}
		else {
			System.out.println(display + " - Invalid");
		}
	}
	//method to display section 2 for each record
	public String display(ArrayList<String> section2) {
		//display elements
		for(String s: section2) {
			return "Expose - Section 2 is: " +s;
		}
		return null;	
	}
	//method to break the message into 2 parts
	public void splitMessage(ArrayList<String> section2) {
		part1List = new ArrayList<String>();
		part2List = new ArrayList<Integer>();

		for (String s : section2) {
			//splits where it sees white space
			String[] parts = s.split("\\s+");
			part1List.add(parts[0]);

			//adds the remaining string in part 2 list
			for (int i = 1; i < parts.length; i++) {
				parts[i].replaceAll(",", "").trim();
				part2List.add(Integer.parseInt(parts[i]));
			}
		}
	}
	public static boolean checkConditions(ArrayList<String> part1List, ArrayList<Integer> part2List) {
		// Check condition 1 - characters must be alphabet letters
		for (String part1 : part1List) {
			if (!part1.matches("[a-zA-Z]+")) {
				return false;
			}
		}
		// Check condition 2- number of characters in Part 1 must be even
		for (String part1 : part1List) {
			if (part1.length() % 2 != 0) {
				return false;
			}
		}
		// Check condition 3- number of tokens in Part 2 must be 4
		if (part2List.size() != 4) {
			return false;
		}
		// Check condition 4- okens in Part 2 must be integers
		for (int part2 : part2List) {
			if (!(part2 >= Integer.MIN_VALUE && part2 <= Integer.MAX_VALUE)) {
				return false;
			}
		}
		// If all conditions are met, return true
		return true;
	}
	public static String CPrime(ArrayList<String> part1List, ArrayList<Integer> part2List) {
		int[][] CPrime = new int[2][1];
		String message = "";
		//getting the part 1 list, say PQLG
		String s = part1List.get(0);


		// Check condition 2
		if (s.length() % 2 != 0) {
			System.out.println("Error: The number of characters in Part 1 is not even in the token " + s);
			return null;
		}
		// Iterate through the part 1 list in pairs of characters
		for (int j = 0; j < s.length(); j += 2) {
			// Get positions of characters in pairs of two in part1List
			int char1 = s.charAt(j) - 'A';
			int char2 = s.charAt(j+1) - 'A';

			// Create matrix C
			int[][] C = {{char1}, {char2}};

			// Create matrix S
			int[][] S = {{part2List.get(0), part2List.get(1)}, {part2List.get(2), part2List.get(3)}};
			
			// Compute (S*C) mod 26
			CPrime[0][0] += ((S[0][0] * C[0][0]) + (S[0][1] * C[1][0])) % 26;
			CPrime[1][0] += ((S[1][0] * C[0][0]) + (S[1][1] * C[1][0])) % 26;
			
			//converting the integers into their positions in the English alphabet
			for (int i = 0; i < CPrime.length; i++) {
			    char c = (char) (CPrime[i][0] + 'A');
			    message += c;
			}
			//setting CPrime to an empty matrix again
			CPrime = new int[][] {{0}, {0}};

		} 
		//System.out.println(message);
		return message;

	}
}


