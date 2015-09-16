package strings;

import java.util.Arrays;
/**
 * http://www.geeksforgeeks.org/remove-all-duplicates-from-the-input-string/
 * Remove all duplicates from the input string.
 * @author user
 *
 */
public class RemoveDuplicatesString {
	public static void main(String[] args) {
		String removeDuplicates = removeDuplicates("geeksforgeeks");
		System.out.println(removeDuplicates);
;	}
	public static String removeDuplicates(String str) {
		char[] charArray = str.toCharArray();
		Arrays.sort(charArray);
		int idxInsertIdx = -1;
		int idxItr = 0, idxItrLeft;
		while(idxItr < charArray.length) {
			idxItrLeft = idxItr + 1;
			boolean matched = false;
			while(idxItrLeft < charArray.length && charArray[idxItr] == charArray[idxItrLeft]) {
				matched = true;
				idxItrLeft++;
			}
			idxInsertIdx++;
			charArray[idxInsertIdx] = charArray[idxItr];
			if(idxItrLeft == charArray.length-1) {
				//all characters seen
			} else {
				idxItr = idxItrLeft;
			}
		}
		if(idxInsertIdx != -1) {
			return new String(charArray,0,idxInsertIdx+1);
		} else {
			return str;
		}
	}
}
