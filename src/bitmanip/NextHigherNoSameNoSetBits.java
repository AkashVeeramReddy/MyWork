package bitmanip;
/**
 * http://www.geeksforgeeks.org/next-higher-number-with-same-number-of-set-bits/
 * 
 * Given a number x, find next number with same number of 1 bits in it’s binary representation.

For example, consider x = 12, whose binary representation is 1100 (excluding leading zeros on 32 bit machine).
 It contains two logic 1 bits. The next higher number with two logic 1 bits is 17 (100012).
 * @author user
 *
 */
public class NextHigherNoSameNoSetBits {
	public static int getNextHigherNo(int number) {
		int res = 0;
		int maskForNo = 1;
		int maskForRes = 1;
		int lastSeen1 = -1;
		int lastLastSeen1 = -1;
		int itrPosRes = -1;
		int bit = 0;
		for (int i = 0; i < Integer.SIZE; i++) {
			bit = number & maskForNo;
			if(bit != 0) {
				lastLastSeen1 = lastSeen1;
				lastSeen1 = i;
				if(lastLastSeen1 != -1) {
					itrPosRes++;
					res = res | maskForRes;
					maskForRes = maskForRes << 1;
				}
			}
			maskForNo = maskForNo << 1;
		}
		if(lastSeen1 != -1) {
			for (int i = itrPosRes; i < lastSeen1; i++) {
				maskForRes = maskForRes << 1;
			}
			res = res | maskForRes;
		}
		return res;
	}
	
	public static void main(String[] args) {
		int nextHigherNo = getNextHigherNo(12);
		System.out.println(nextHigherNo);
	}
}
