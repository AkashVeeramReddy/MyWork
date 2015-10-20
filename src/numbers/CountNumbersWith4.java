package numbers;


/**
 * http://www.geeksforgeeks.org/count-numbers-from-1-to-n-that-have-4-as-a-a-
 * digit/
 * 
 * Count numbers from 1 to n that have 4 as a a digit Given a number n, find
 * count of all numbers from 1 to n that have 4 as a a digit.
 * 
 * Examples:
 * 
 * Input: n = 5 Output: 1 Only 4 has '4' as digit
 * 
 * Input: n = 50 Output: 14
 * 
 * Input: n = 328 Output: 60
 * 
 * @author user
 * 
 */
public class CountNumbersWith4 {
	public static void main(String[] args) {
		int countNumbersWith4 = countNumbersWith4(528);
		System.out.println(countNumbersWith4);
	}
	public static int countNumbersWith4(int no) {
		int length = (no + "").length();
		int info[] = new int[length - 1];
		//no of 4s in 1..9. So info[1] will contain no of 4s in 1..99
		info[0] = 1;
		int power = 10;
		for (int i = 1; i < info.length; i++) {
			info[i] = power + 9*info[i-1];
			power = power*10;
		}
		return countNumbersWith4(no, power, length,info);
	}
	
	public static int countNumbersWith4(int no, int powerOf10, int noOfDigit,int info[]) {
		if(no < 4)
			return 0;
		if(no>= 4 && no<=9)
			return 1;
		int msb = no / powerOf10;
		int rem = no % powerOf10;
		if(msb == 4) {
			return 4*info[noOfDigit-2] + rem + 1;
		} else if(msb < 4) {
			return (msb)*info[noOfDigit-2] +
					countNumbersWith4(rem, powerOf10/10, noOfDigit-1, info);
		} else {
			//msb > 4
			return (msb-1)*info[noOfDigit-2] + powerOf10+
					countNumbersWith4(rem, powerOf10/10, noOfDigit-1, info);
		}
	}
}
