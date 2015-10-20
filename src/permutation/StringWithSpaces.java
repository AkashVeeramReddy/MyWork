package permutation;

import utils.ArrayUtilities;

/**
 * http://www.geeksforgeeks.org/print-possible-strings-can-made-placing-spaces/
 * Given a string you need to print all possible strings that can be made by placing spaces (zero or one) in between them.

Input:  str[] = "ABC"
Output: ABC
        AB C
        A BC
        A B C
 * @author user
 *
 */
public class StringWithSpaces {
	public static void main(String[] args) {
		printWithSpaces("ABC");
	}
	public static void printWithSpaces(String str) {
		int length = str.length();
		char[]printArray = new char[2*length - 1];
		printWithSpaces(str.toCharArray(),printArray,0,0);
	}

	private static void printWithSpaces(char[] charArray, char[] printArray,
			int fromIdx,int printArrayItr) {
		if(fromIdx == charArray.length) {
			ArrayUtilities.printCharArrayTill(printArray, printArrayItr);
		} else {
			printArray[printArrayItr] = charArray[fromIdx];
			printWithSpaces(charArray, printArray, fromIdx+1, printArrayItr+1);
			if(fromIdx != charArray.length-1) {
				printArray[printArrayItr+1] = ' ';
				printWithSpaces(charArray, printArray, fromIdx+1, printArrayItr+2);
			}
		}
	}
	
	
}
