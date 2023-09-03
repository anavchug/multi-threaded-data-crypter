package threads;

import java.util.ArrayList;

class Reveal implements Runnable {
	private ArrayList<String> section1;

	public Reveal(ArrayList<String> section1) {
		this.section1 = section1;
	}
	@Override
	public void run() {
		ArrayList<Integer> intList = parseIntList(section1);
		sumOfNums(intList);
		calculateM(intList);
		mMinusIntegers(intList);
		if(checkResultList(mMinusIntegers(intList)) == true){
			String result = getResultString(intList);
			System.out.println(display(section1) +" - " +result + " - valid");
		}
		else {
			System.out.println(display(section1) +" - Invalid");	
		}

	}
	public String display(ArrayList<String> section1) {
		//display elements
		for(String s: section1) {
			return "Reveal- Section 1 is: " +s;
		}
		return null;	
	}
	//method to convert the String Array List to an Integer Array List
	public static ArrayList<Integer> parseIntList(ArrayList<String> stringList) {
		ArrayList<Integer> intList = new ArrayList<>();
		
		for (String s : stringList) {
			String[] stringArray = s.split("\\s+");
			//String[] stringArray = s.split(" ");
			for (String str : stringArray) {
				str = str.replaceAll(",", "").trim(); // remove commas and white spaces
				if (!str.equals("")) { // add this check to avoid empty strings
					intList.add(Integer.parseInt(str));
				}
			}
		}
		return intList;
	}

	public static int sumOfNums(ArrayList<Integer> intList) {
		int sum = 0;
		for (int i : intList) {
			sum += i;
		}
		return sum;
	}
	public int calculateM(ArrayList<Integer> intList) {
		int M = sumOfNums(intList)% 26;
		//System.out.println("M value: " + M);
		return M;
	}

	public static ArrayList<Integer> mMinusIntegers(ArrayList<Integer> intList) {
		int M = sumOfNums(intList) % 26;
		ArrayList<Integer> resultList = new ArrayList<Integer>();

		for (int i = 0; i < intList.size(); i++) {
			int result = (M - intList.get(i)) % 26;
			resultList.add(result);
		}

		//System.out.println("Result: " + resultList);
		return resultList;
	}

	//converts the Integer ArrayList to the corresponding String 
	public static String convertIntListToString(ArrayList<Integer> intList) {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < intList.size(); i++) {
			char c = (char) (intList.get(i) + 'A');
			sb.append(c);
		}

		return sb.toString();
	}

	//Printing the final String with the message
	public static String getResultString(ArrayList<Integer> intList) {
		ArrayList<Integer> resultList = mMinusIntegers(intList);
		String resultString = convertIntListToString(resultList);
		return "The revealed code is: " + resultString;
	}

	public static boolean checkResultList(ArrayList<Integer> resultList) {
		for (int i = 0; i < resultList.size(); i++) {
			if (resultList.get(i) < 0) {
				return false;
			}
		}
		return true;
	}
}