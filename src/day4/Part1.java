package day4;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

public class Part1 {

	/*
	 * 
	 * Puzzle input: 125730-579381
	 * 
	 */
	
	
	public static void main(String[] args) {
		System.out.println(countOkPasswords());
//		System.out.println(checkCriteria(111145));
	}
	
	public static int countOkPasswords() {
		int okPasswords = 0;
		
		for(int i = 125731; i < 579381; i++) {
			if(checkCriteria(i)) {
				okPasswords++;
			}
		}
		
		return okPasswords;
	}
	
	public static boolean checkCriteria(int password) {
		
		String pass = Integer.toString(password);
		
		if(pass.length() != 6) {
			return false;
		}

		//boolean repeatDigit = false;
		char pChar = '\u0000';
		HashMap<Character, Integer> numCount = new HashMap<Character, Integer>();
		
		for(int i = 0; i < pass.length(); i++) {
			char cChar = pass.charAt(i);
			if(numCount.containsKey(cChar)) {
				numCount.put(cChar, numCount.get(cChar) + 1);
			} else {
				numCount.put(cChar, 1);
			}
			
			/*if(pChar != '\u0000' && pChar == cChar) {
				repeatDigit = true;
			}*/
			
			if(pChar != '\u0000' && Character.getNumericValue(cChar) < Character.getNumericValue(pChar)) {
				return false;
			}
			
			pChar = cChar;
		}
		// below is criteria for part 2
		Set<Entry<Character, Integer>> es = numCount.entrySet();
		boolean containsTwo = false;
		for(Entry<Character, Integer> e : es) {
			if(e.getValue() == 2) {
				containsTwo = true;
			}
		}
		
		if(!containsTwo) {
			return false;
		}
		// above is criteria for part 2
		
		/*if(repeatDigit == false) {
			return false;
		}*/

		return true;
	}
}
