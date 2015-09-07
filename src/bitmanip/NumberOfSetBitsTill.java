package bitmanip;
/**
 * http://www.geeksforgeeks.org/count-total-set-bits-in-all-numbers-from-1-to-n/
 * 
 * Given a positive integer n, count the total number of set bits in binary representation of all numbers from 1 to n.

Examples:

Input: n = 3
Output:  4

Input: n = 6
Output: 9

Input: n = 7
Output: 12

Input: n = 8
Output: 13
 * @author user
 *
 */
public class NumberOfSetBitsTill {
	public static void main(String[] args) {
		System.out.println(numberOfSetBitsTill(6));
	}
	public static int numberOfSetBitsTill(int num) {
		int itr = 2;
		int prevItr = 1;
		int sum = 0;
		while (num>=prevItr) {
			int noOfEleInLastGrp = (num + 1)%itr;
			sum = sum + ((noOfEleInLastGrp>prevItr)?(noOfEleInLastGrp - prevItr):0);
			int noOfGroupsB4Last = (num + 1)/itr;
			sum = sum + noOfGroupsB4Last*prevItr;
			prevItr = itr;
			itr = itr*2;
		}
		return sum;
	}
}
