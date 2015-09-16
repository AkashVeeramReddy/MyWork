package strings;
/**
 * http://www.geeksforgeeks.org/remove-a-and-bc-from-a-given-string/
 * Remove “b” and “ac” from a given string
Given a string, eliminate all “b” and “ac” in the string, you have to replace them in-place,
 and you are only allowed to iterate over the string once
 * @author user
 *
 */
public class RemoveBAndAC {
	public static void main(String[] args) {
		String removeBAndAC = removeBAndAC("aabacbccd");
		System.out.println(removeBAndAC);
	}
	public static String removeBAndAC(String str) {
		char[] charArray = str.toCharArray();
		int insertIdx = 0;
		int noOfASeen = 0;
		for (int itr = 0; itr < charArray.length;) {
			if(charArray[itr] == 'a') {
				noOfASeen++;
				itr++;
				charArray[insertIdx] = charArray[itr];
				insertIdx++;
			} else if(charArray[itr] == 'b') {
				itr++;
			} else if(charArray[itr] == 'c') {
				if(noOfASeen > 0) {
					noOfASeen--;
					insertIdx--;
				} else {
					charArray[insertIdx] = charArray[itr];
					insertIdx++;
					noOfASeen = 0;
				}
				itr++;
			} else {
				charArray[insertIdx] = charArray[itr];
				insertIdx++;
				noOfASeen = 0;
				itr++;
			}
		}
		return new String(charArray,0,insertIdx);
	}
}
