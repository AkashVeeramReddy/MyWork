package permutation;

import java.util.Arrays;

/**
 * http://www.geeksforgeeks.org/print-all-interleavings-of-given-two-strings/
 * Print all interleavings of given two strings Given two strings str1 and str2,
 * write a function that prints all interleavings of the given two strings
 * 
 * Input: str1 = "AB",  str2 = "CD"
Output:
    ABCD
    ACBD
    ACDB
    CABD
    CADB
    CDAB

Input: str1 = "AB",  str2 = "C"
Output:
    ABC
    ACB
    CAB
 * @author user
 * 
 */
public class PrintAllInterleaving {
	public static void main(String[] args) {
		printAllInterleaving("AB", "CD");
	}
	public static void printAllInterleaving(String str1, String str2) {
		printAllInterleaving(str1.toCharArray(), str2.toCharArray());
	}

	public static void printAllInterleaving(char[] charArray, char[] charArray2) {
		int length1 = charArray.length;
		int length2 = charArray2.length;
		char []printArray = new char[length1 + length2];
		printAllInterleaving(charArray, charArray2,printArray,length1,length2, 0, 0,0);
		
	}

	private static void printAllInterleaving(char[] charArray,
			char[] charArray2, char[] printArray, int length1, int length2,
			int idxStr1, int idxStr2, int printIdx) {
		if(printIdx == (length1 + length2)) {
			System.out.println(Arrays.toString(printArray));
		} else {
			if(idxStr1 < length1) {
				printArray[printIdx] = charArray[idxStr1];
				printAllInterleaving(charArray, charArray2,printArray,length1,length2,idxStr1+1,idxStr2,printIdx+1);
			}
			if(idxStr2 < length2) {
				printArray[printIdx] = charArray2[idxStr2];
				printAllInterleaving(charArray, charArray2,printArray,length1,length2,idxStr1,idxStr2+1,printIdx+1);
			}
		}
	}
}
