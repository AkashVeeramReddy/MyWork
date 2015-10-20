package numbers;
/**
 * Fraction to Recurring Decimal My Submissions Question Solution 
Total Accepted: 20317 Total Submissions: 149991 Difficulty: Medium
Given two integers representing the numerator and denominator of a fraction, return the fraction in string format.

If the fractional part is repeating, enclose the repeating part in parentheses.

For example,

Given numerator = 1, denominator = 2, return "0.5".
Given numerator = 2, denominator = 1, return "2".
Given numerator = 2, denominator = 3, return "0.(6)".
 * @author user
 *
 */
public class FractionToDecimal {
	public static String fractionToDecimal(int numerator, int denominator) {
		double ans = (double)numerator/(double)denominator;
		System.out.println(ans);
		return Double.toString(ans);
	}
	
	public static void main(String[] args) {
		int num = 1;
		int den = 13;
		String fractionToDecimal = fractionToDecimal(num, den);
		num = 1;
		den = 104;
		/**
		 * 0.07692307692307693
0.009615384615384616
		 */
		fractionToDecimal = fractionToDecimal(num, den);
		System.out.println(fractionToDecimal);
		double check = 0.0096153846*8;
	}
}
