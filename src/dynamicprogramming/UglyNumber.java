package dynamicprogramming;

/**
 * https://leetcode.com/problems/ugly-number-ii/
 * www.geeksforgeeks.org/ugly-numbers/
 * 
 * Write a program to find the n-th ugly number.
 * 
 * Ugly numbers are positive numbers whose prime factors only include 2, 3, 5.
 * For example, 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 is the sequence of the first 10
 * ugly numbers.
 * 
 * Note that 1 is typically treated as an ugly number.
 * 
 * @author user
 * 
 */
public class UglyNumber {
	
	public static void main(String[] args) {
		int nthUglyNumber = nthUglyNumber(9);
		System.out.println(nthUglyNumber);
	}
	public static int nthUglyNumber(int n) {
        int ugly[] = new int[n];
        ugly[0] = 1;
        int uglyIdx2 = 0;
        int uglyIdx3 = 0;
        int uglyIdx5 = 0;
        int uglyNo2 = 2;
        int uglyNo3 = 3;
        int uglyNo5 = 5;
        int min;
        for (int i = 2; i <=n; i++) {
        	uglyNo2 = 2*ugly[uglyIdx2];
        	uglyNo3 = 3*ugly[uglyIdx3];
        	uglyNo5 = 5*ugly[uglyIdx5];
        	min = Math.min(uglyNo2, Math.min(uglyNo3, uglyNo5));
        	ugly[i-1] = min;
        	if(min == uglyNo2) {
        		uglyIdx2++;
        	}
        	if(min == uglyNo3) {
        		uglyIdx3++;
        	}
        	if(min == uglyNo5) {
        		uglyIdx5++;
        	}
        }
        return ugly[n-1];
    }
}
