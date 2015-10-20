package numbers;

/**
 * https://leetcode.com/problems/number-of-digit-one/
 * 
 * Given an integer n, count the total number of digit 1 appearing in all
 * non-negative integers less than or equal to n.
 * 
 * For example: Given n = 13, Return 6, because digit 1 occurred in the
 * following numbers: 1, 10, 11, 12, 13.
 * 
 * @author user
 * 
 */
public class NumberOfDigitOne {
	public static void main(String[] args) {
		int countNumbersWith4 = countDigitOne(1999);
		System.out.println(countNumbersWith4);
	}
	public static int countDigitOne(int n) {
		int length = (n + "").length();
		int info[] = new int[length - 1];
		//no of digits with 1s in 1..9. So info[1] will contain no of 1s in 1..99
		info[0] = 1;
		int power = 10;
		for (int i = 1; i < info.length; i++) {
			info[i] = power + 10*info[i-1];
			power = power*10;
		}
		return noOfDigitsWithOnes(n, power, length,info);
	}
	
	public static int noOfDigitsWithOnes(int no, int powerOf10, int noOfDigit,int info[]) {
		if(no < 1)
			return 0;
		if(no>= 1 && no<=9)
			return 1;
		int msb = no / powerOf10;
		int rem = no % powerOf10;
		if(msb == 1) {
			return info[noOfDigit-2] + (rem + 1)
					+ noOfDigitsWithOnes(rem, powerOf10/10, noOfDigit-1, info);
		} else if(msb < 1) {
			return noOfDigitsWithOnes(rem, powerOf10/10, noOfDigit-1, info);
		} else {
			//msb > 1
			return (msb)*info[noOfDigit-2] + powerOf10+
					noOfDigitsWithOnes(rem, powerOf10/10, noOfDigit-1, info);
		}
	}
}
