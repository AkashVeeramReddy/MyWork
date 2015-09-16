package pattern;


public class RabinKarpStringMatcher {
	public static final int ALPHABET_SIZE = 26;
	public static final int PRIME_NO = 101;
	protected final int patternModulus;
	protected final String pattern;
	protected final char[] patternCharArray;
	protected final int patternLength;
	protected final int multiFactorPattern;
	
	public RabinKarpStringMatcher(String pattern) {
		this.pattern = pattern;
		patternCharArray = pattern.toCharArray();
		patternLength = pattern.length();
		patternModulus = getPatternModulus();
		multiFactorPattern = ((int)(Math.pow(ALPHABET_SIZE, patternLength-1)))%PRIME_NO;
	}

	protected int getPatternModulus() {
		int patternModulus = 0;
		for (int i = 0; i < patternLength; i++) {
			patternModulus = (ALPHABET_SIZE*patternModulus + getValue(patternCharArray[i]))%PRIME_NO;
		}
		return patternModulus;
	}
	
	public int getValue(char ch) {
		return ch - 'a';
	}
	
	public boolean printAllOccurences(String text) {
		if(text.length() < patternLength ) {
			return false;
		}
		char[] textArray = text.toCharArray();
		boolean isOcc = false;
		int modulus = 0;
		for (int i = 0; i < patternLength; i++) {
			modulus = (ALPHABET_SIZE*modulus + getValue(textArray[i]))%PRIME_NO;
		}
		if(modulus == patternModulus) {
			System.out.println("Pattern occurs at 0");
			isOcc = true;
		}
		for (int i = patternLength; i < textArray.length; i++) {
			modulus = (ALPHABET_SIZE*(modulus - multiFactorPattern*(getValue(textArray[i - patternLength])))
					+ getValue(textArray[i]))%PRIME_NO;
			if(modulus == patternModulus) {
				System.out.println("Pattern occurs at "+(i - patternLength + 1));
				isOcc = true;
			}
		}
		return isOcc;
	}
	
}
