package strings;

import java.util.Iterator;

public class RotatedString {
	
	/**
	 * Assume you have a method isSubstring which checks if one word is a substring of another. 
	 * Given two strings, s1 and s2, write code to check if s2 is a rotation of s1 using only
	 *  one call to isSubstring (i.e., “waterbottle” is a rotation of “erbottlewat”).
	 * @param str
	 * @param stringToCheck
	 * @return
	 */
	public static boolean isRotatedString(String str,String stringToCheck) {
		if(str.length() != stringToCheck.length())
			return false;
		char firstChar = str.charAt(0);
		int indexOf = stringToCheck.indexOf(firstChar);
		while (indexOf != -1) {
			boolean rotatedStringFromIndex = isRotatedStringFromIndex(str, stringToCheck, indexOf);
			if(rotatedStringFromIndex) {
				//call substring to verify it
				return true;
			}
			indexOf = stringToCheck.indexOf(firstChar, indexOf+1);
		}
		return false;
	}
	
	public static boolean isRotatedStringFromIndex(String str,String stringToCheck,int indexInSecondString) {
		for(int i = 0;i<str.length();i++) {
			if(str.charAt(i) != stringToCheck.charAt((indexInSecondString + i)%str.length()))
					return false;
		}
		return true;
	}
	
	public static void main(String[] args) {
		boolean rotatedString = isRotatedString("waterwbottle", "ewaterwbottl");
		System.out.println(rotatedString);
	}
}
