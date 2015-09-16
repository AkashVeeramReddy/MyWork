package pattern;

import java.util.Arrays;

import utils.MyConstants;

public class KMPStringMatcher {
	
	protected final String pattern;
	protected final char[] patternArray;
	protected final int patternLength;
	/**
	 * If transitionArr[i] = j means 0..i characters have matched for the pattern and the text,
	 * but the next character for both are not matching.
	 * has not been matched then the next check should start from (j+1) th character of pattern
	 * 
	 *  Say out  pattern is 'aabaac'. Out text is 'aabaabac'
	 *  pat[0..4] = text[0..4] and pat[5] != text[5]. So our next check should be
	 *  between text[5] and pat[transitionArr[4] + 1] 
	 */
	
	protected final int[] transitionArr;
	
	public KMPStringMatcher(String pattern) {
		this.pattern = pattern;
		patternArray = pattern.toCharArray();
		patternLength = pattern.length();
		transitionArr = new int[patternLength];
		initializeTransitionArrayOpt();
	}
	
	protected void initializeTransitionArrayOpt() {
		transitionArr[0] = -1;
		int patternIdx = 0;
		for (int transItr = 1; transItr < patternLength; ) {
			if(patternArray[patternIdx] == patternArray[transItr]) {
				//characters match
				transitionArr[transItr] = patternIdx;
				patternIdx++;
				transItr++;
			} else {
				//characters doesnt match
				if(patternIdx == 0) {
					//cant go back
					transitionArr[transItr] = -1;
					transItr++;
				} else {
					/**
					 * Why patternIdx-1??
					 * Because we are checking patternArray[patternIdx] == patternArray[transItr]
					 * which means patternArray[patternIdx-1] contains character previous to
					 * patternArray[transItr]. Now we have to get longest prefix which
					 * is suffix of patter[1..patternIdx-1] which is transitionArr[patternIdx-1]
					 */
					patternIdx = transitionArr[patternIdx-1];
				}
				patternIdx++;
			}
		}
		
	}
	/**
	 * not optimal. just brute force
	 */
	protected void initializeTransitionArray() {
		transitionArr[0] = -1;
		for (int i = 1; i < patternLength; i++) {
			boolean isSet = false;
			for (int j = i-1; j >=0; j--) {
				boolean isEqual = PatternUtils.isEqual(patternArray, 0, j, patternArray, i-j, i);
				if(isEqual) {
					transitionArr[i] = j;
					isSet = true;
					break;
				}
			}
			if(!isSet)
				transitionArr[i] = -1;
		}
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Pattern: ");
		builder.append(pattern);
		builder.append(MyConstants.NEW_LINE);
		builder.append(Arrays.toString(transitionArr));
		return builder.toString();
	}
	
	public boolean printAllOccurences(String searchStr) {
		int patternIdx = 0;
		int strIdx = 0;
		char []str = searchStr.toCharArray();
		boolean isOcc = false;
		while(strIdx < str.length) {
			if(str[strIdx] == patternArray[patternIdx]) {
				if(patternIdx == (patternLength-1)) {
					//entire string matched
					isOcc = true;
					System.out.println("Occurs from index "+(strIdx - patternLength + 1));
					patternIdx = transitionArr[patternIdx];
				}
				patternIdx++;
				strIdx++;
			} else {
				if(patternIdx == 0) {
					//1st character itself didnt match
					strIdx++;
				} else {
					patternIdx = transitionArr[patternIdx-1];
					if(patternIdx == -1) {
						strIdx++;
						patternIdx = 0;
					} else {
						//patternIdx >=0. Do a shift
						patternIdx = patternIdx+1;
					}
				}
			}
		}
		return isOcc;
	}
	
}
