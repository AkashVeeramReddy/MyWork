package strings;
/**
 * http://www.geeksforgeeks.org/recursively-remove-adjacent-duplicates-given-string/
 * 
 * Recursively remove all adjacent duplicates
Given a string, recursively remove adjacent duplicate characters from string. 
The output string should not have any adjacent duplicates. See following examples.

Input:  azxxzy
Output: ay
First "azxxzy" is reduced to "azzy". The string "azzy" contains duplicates, 
so it is further reduced to "ay".

Input: geeksforgeeg
Output: gksfor
First "geeksforgeeg" is reduced to "gksforgg". The string "gksforgg" contains 
duplicates, so it is further reduced to "gksfor".

Input: caaabbbaacdddd
Output: Empty String

Input: acaaabbbacdddd
Output: acac
 * @author user
 *
 */
public class RemoveAdjDuplicates {
	public static void main(String[] args) {
		String removeDuplicates = removeAdjDuplicates("geeksforgeeg");
		System.out.println(removeDuplicates);
	}
	public static String removeAdjDuplicates(String str) {
		char[] charArray = str.toCharArray();
		int insertIdx = 0;
		for (int i = 0; i < charArray.length; i++) {
			char chItr = charArray[i];
			if(insertIdx == 0) {
				charArray[insertIdx] = chItr;
				insertIdx++;
			} else {
				char ch = charArray[insertIdx - 1];
				if(ch == chItr) {
					int itr = i+1;
					while(itr < charArray.length) {
						if(charArray[itr] == ch) {
							itr++;
						} else {
							i = itr;
							//next iteration of for loop should start from i. So i-- and i++ will get cancelled
							i--;
							break;
						}
					}
					insertIdx--;
				} else {
					charArray[insertIdx] = chItr;
					insertIdx++;
				}
			}
		}
		return new String(charArray, 0, insertIdx);
	}
}
