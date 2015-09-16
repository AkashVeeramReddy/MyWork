package strings;

/**
 * http://www.geeksforgeeks.org/find-the-smallest-window-in-a-string-containing-all-characters-of-another-string/
 * 
 * Find the smallest window in a string containing all characters of another
 * string Given two strings string1 and string2, find the smallest substring in
 * string1 containing all characters of string2 efficiently.
 * 
 * For Example:
 * 
 * Input string1: “this is a test string” Input string2: “tist” Output string:
 * “t stri”
 * 
 * @author user
 * 
 */
public class SmallestWindowOfAllCharacters {
	public static final int ALPHABET_SIZE = 26;
	
	public static void main(String[] args) {
		//String window = getWindow("this is a test string", "tist");
		String window = getWindow("aaeeba", "abe");
		System.out.println(window);
	}
	public static String getWindow(String text, String input) {
		return getWindow(text.toCharArray(), input.toCharArray());
	}
	public static String getWindow(char []text, char [] input) {
		boolean []hashInput = new boolean[ALPHABET_SIZE];
		int []countWindow = new int[ALPHABET_SIZE];
		int noOfUniqueCharInput = 0;
		for (int i = 0; i < input.length; i++) {
			boolean b = hashInput[input[i] - 'a'];
			if(!b) {
				noOfUniqueCharInput++;
				hashInput[input[i] - 'a'] = true;
			}
		}
		int i = 0;
		int uniqCharWindow = 0;
		int minLength = 0;
		for (; i < text.length; i++) {
			if(!hashInput[text[i] - 'a']) {
				continue;
			}
			int b = countWindow[text[i] - 'a'];
			if(b == 0) {
				uniqCharWindow++;
			}
			countWindow[text[i] - 'a']++;
			if(noOfUniqueCharInput == uniqCharWindow) {
				minLength = i+1;
				break;
			}
		}
		if(uniqCharWindow < noOfUniqueCharInput) {
			return null;
		}
		int starOfMinWindow = 0;
		int startWindow = 0;
		int curWindowLength = 0;
		for (int endWindowPlus1 = i+1; endWindowPlus1 < text.length; endWindowPlus1++) {
			char chAtEndWindowPlus1 = text[endWindowPlus1];
			if(!hashInput[chAtEndWindowPlus1 - 'a']) {
				continue;
			}
			countWindow[chAtEndWindowPlus1 - 'a']++;
			while(true) {
				char chAtStartWindow = text[startWindow];
				int count = countWindow[chAtStartWindow - 'a'];
				if(count > 1) {
					startWindow++;
					countWindow[chAtStartWindow - 'a']--;
				} else {
					break;
				}
			}
			curWindowLength = endWindowPlus1 - startWindow + 1;
			if(curWindowLength < minLength) {
				minLength = curWindowLength;
				starOfMinWindow = startWindow;
			}
		}
		return new String(text).substring(starOfMinWindow, starOfMinWindow + minLength);
	}
}
