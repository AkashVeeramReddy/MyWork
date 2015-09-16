package pattern;

import utils.MyUtilities;

public class StringMatcherFa {
	private static final int NO_OF_ALPHABETS = 3;
	protected final String pattern;
	protected final char[] patternArray;
	protected final int patternLength;
	protected Integer[][] fa;
	
	public StringMatcherFa(String pattern) {
		this.pattern = pattern;
		patternArray = pattern.toCharArray();
		patternLength = pattern.length();
		createFiniteAutomata();
	}
	/**
	 * not optimal. Optimal should use transition like fa string matcher
	 */
	protected void createFiniteAutomata() {
		fa = new Integer[patternLength+1][NO_OF_ALPHABETS];
		char[] stringSeenSoFar = new char[patternLength+1];
		for (int i = 0; i <= patternLength; i++) {
			for (char chItr = 'a';  chItr < 'a' + NO_OF_ALPHABETS; chItr++) {
				stringSeenSoFar[i] = chItr;
				int indexOfChForFa = getIndexOfChForFa(chItr);
				fa[i][indexOfChForFa] = getDestState(stringSeenSoFar, Math.min(patternLength-1, i),i);
			}
			if(i != patternLength)
				stringSeenSoFar[i] = patternArray[i];
		}
	}
	/**
	 * 
	 * @param stringSeenSoFar
	 * @param patternIdx - idx frm where the scanning of the pattern have to be done backwards
	 * @return
	 */
	protected int getDestState(char[] stringSeenSoFar, int patternIdx, int endIdxStringSeen) {
		int startIdxPattern = 0;
		int startIdxStringSeen, endIdxPattern;
		for (int lengthItr = patternIdx+1; lengthItr > 0; lengthItr--) {
			startIdxStringSeen = endIdxStringSeen - lengthItr + 1;
			endIdxPattern = lengthItr + startIdxPattern - 1;
			boolean isEqual = PatternUtils.isEqual(stringSeenSoFar, startIdxStringSeen, endIdxStringSeen,
					patternArray, startIdxPattern, endIdxPattern);
			if(isEqual) {
				return lengthItr;
			}
		}
		return 0;
	}
	
	public boolean printAllOccurences(String searchStr) {
		int state = 0;
		char chItr;
		boolean isOccur = false;;
		for (int i = 0; i < searchStr.length(); i++) {
			chItr = searchStr.charAt(i);
			state = fa[state][getIndexOfChForFa(chItr)];
			if(state == patternLength) {
				System.out.println("Occurs from index "+(i - patternLength + 1));
				isOccur = true;
			}
		}
		return isOccur;
	}
	
	protected int getIndexOfChForFa(char ch) {
		return ch - 'a';
	}
	
	public void printFA() {
		MyUtilities.printDoubleDimensionalArray(fa, 5);
	}
}
