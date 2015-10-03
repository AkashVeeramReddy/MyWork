package dynamicprogramming;
import sun.security.util.Length;

/**
 * https://www.codechef.com/problems/ABCSTR
 * You are given an ABC-string S. You need to count the number of substrings of S, which have an equal number of 'A'-s, 'B'-s and 'C'-s.
 * 
 * @author user
 *
 */
public class NoOfSubstringEqualABC {
	
	public static class Info {
		public int noOfFirstChar;
		public int noOfSecondChar;
		public int noOfThirdChar;
	}
	
	public static long getNumberofSubstrings(String str) {
		int len = str.length();
		if(len < 3) {
			return 0;
		}
		char[] charArray = str.toCharArray();
		Info[] infoArray = new Info[len+1];
		infoArray[0] = new Info();
		int seenFirstChar = 0;
		int seenSecondChar = 0;
		int seenThirdChar = 0;
		for(int i=0;i<len;i++) {
			switch (charArray[i]) {
			case 'A':
				seenFirstChar++;
				break;
			case 'B':
				seenSecondChar++;
				break;
			case 'C':
				seenThirdChar++;
				break;
			default:
				break;
			}
			Info info = new Info();
			info.noOfFirstChar = seenFirstChar;
			info.noOfSecondChar = seenSecondChar;
			info.noOfThirdChar = seenThirdChar;
			infoArray[i+1] = info;
		}
		long[]count = new long[len];
		int endIdx;
		for(int lenItr = 3;lenItr <= len; lenItr = lenItr + 3) {
			for(int startIdx = 0; startIdx <= len - lenItr;startIdx++) {
				endIdx = startIdx + lenItr - 1;
				Info startInfo = infoArray[startIdx];
				Info endInfo = infoArray[endIdx+1];
				
				int diffFirstChar = endInfo.noOfFirstChar - startInfo.noOfFirstChar;
				int diffSecondChar = endInfo.noOfSecondChar - startInfo.noOfSecondChar;
				int diffThirdChar = endInfo.noOfThirdChar - startInfo.noOfThirdChar;
				
				if(diffFirstChar == diffSecondChar && diffSecondChar == diffThirdChar) {
					count[startIdx]++;
				}
			}
		}
		long sum = 0;
		for(int i=0;i<len;i++) {
			sum = sum + count[i];
		}
		return sum;
		
	}
	
	public static void main(String[] args) {
		long numberofSubstrings = getNumberofSubstrings("ABACABA");
		System.out.println(numberofSubstrings);
	}
}


