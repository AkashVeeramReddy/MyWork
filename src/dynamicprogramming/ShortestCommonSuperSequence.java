package dynamicprogramming;
/**
 * http://www.geeksforgeeks.org/shortest-common-supersequence/
 * 
 * Shortest Common Supersequence
Given two strings str1 and str2, find the shortest string that has both str1 and str2
 as subsequences.

Examples:

Input:   str1 = "geek",  str2 = "eke"
Output: "geeke"

Input:   str1 = "AGGTAB",  str2 = "GXTXAYB"
Output:  "AGXGTXAYB"
 * @author user
 *
 */
public class ShortestCommonSuperSequence {
	
	public static void main(String[] args) {
		printShortestCommonSuperSequence("AGGTAB", "GXTXAYB");
	}
	public static void printShortestCommonSuperSequence(String str1, String str2) {
		int lengthStr1 = str1.length();
		int lengthStr2 = str2.length();
		Info infoArray[][] = new Info[lengthStr1+1][lengthStr2+1];
		infoArray[0][0] = new Info();
		for (int i = 0; i < lengthStr1; i++) {
			Info info = new Info();
			info.fromWhere = 0;
			info.length = i;
			infoArray[i+1][0] = info;
		}
		for (int j = 0; j < lengthStr2; j++) {
			Info info = new Info();
			info.fromWhere = 1;
			info.length = j;
			infoArray[0][j+1] = info;
		}
		for (int i = 0; i < lengthStr1; i++) {
			for (int j = 0; j < lengthStr2; j++) {
				char charAtStr1 = str1.charAt(i);
				char charAtStr2 = str2.charAt(j);
				if(charAtStr1 == charAtStr2) {
					Info info = new Info();
					info.fromWhere = 2;
					info.length = infoArray[i][j].length + 1;
					infoArray[i+1][j+1] = info;
				} else {
					Info info1 = infoArray[i][j+1];
					Info info2 = infoArray[i+1][j];
					Info info = new Info();
					infoArray[i+1][j+1] = info;
					if(info1.length < info2.length) {
						info.length = info1.length+1;
						info.fromWhere = 0;
					} else {
						info.length = info2.length+1;
						info.fromWhere = 1;
					}
					
				}
				
			}
		}
		printString(infoArray,lengthStr1,lengthStr2,str1,str2);
		
	}
	
	private static void printString(Info[][] infoArray, int idxStr1,
			int idxStr2,String str1,String str2) {
		if(idxStr1 == 0 && idxStr2 == 0) {
			System.out.println();
			return;
		} else {
			Info info = infoArray[idxStr1][idxStr2];
			switch (info.fromWhere) {
			case 0:
				printString(infoArray, idxStr1-1, idxStr2,str1,str2);
				System.out.print(str1.charAt(idxStr1-1));
				break;
			case 1:
				printString(infoArray, idxStr1, idxStr2-1,str1,str2);
				System.out.print(str2.charAt(idxStr2-1));
				break;
			case 2:
				printString(infoArray, idxStr1-1, idxStr2-1,str1,str2);
				System.out.print(str1.charAt(idxStr1-1));
				break;
			default:
				break;
			}
		}
	}

	public static class Info {
		/**
		 * 0 from 1st string, 1 from 2nd string, 2 from  both
		 */
		public int fromWhere = -1;
		public int length = 0;
	}
}
