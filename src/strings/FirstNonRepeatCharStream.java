package strings;

import java.util.LinkedList;

/**
 * http://www.geeksforgeeks.org/find-first-non-repeating-character-stream-characters/
 * Find the first non-repeating character from a stream of characters
Given a stream of characters, find the first non-repeating character from stream. You need to tell the first
 non-repeating character in O(1) time at any moment.
 * @author user
 *
 */
public class FirstNonRepeatCharStream {
	private static final int ALPHABET_SIZE = 26;
	
	public static void main(String[] args) {
		String stream = "geeksforgeeksandgeeksquizfor";
		findFirstNonRepeatCharStream(stream);
	}

	public static void findFirstNonRepeatCharStream(String str) {
		char[] charArray = str.toCharArray();
		LinkedList<Character> stack = new LinkedList<Character>();
		int []countArray = new int[ALPHABET_SIZE];
		Character nonRepeatingChar = null;
		for (int i = 0; i < charArray.length; i++) {
			char ch = charArray[i];
			int j = countArray[ch - 'a'];
			if(j == 0) {
				//a poss of non repeating char
				stack.addLast(ch);
			} else {
				//char exists
				if(ch == nonRepeatingChar) {
					stack.removeFirst();
					while(!stack.isEmpty()) {
						Character first = stack.getFirst();
						int count = countArray[first - 'a'];
						if(count == 1) {
							break;
						} else {
							stack.removeFirst();
						}
					}
				}
			}
			if(stack.isEmpty()) {
				nonRepeatingChar = null;
			} else {
				nonRepeatingChar = stack.getFirst();
			}
			countArray[ch - 'a']++;
			System.out.println("Non repeating char at idx "+i+" is "+nonRepeatingChar);
		}
	}
}
