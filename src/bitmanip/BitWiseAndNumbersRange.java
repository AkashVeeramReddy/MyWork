package bitmanip;

import java.util.Arrays;

/**
 * Bitwise AND of Numbers Range
 * 
 * Given a range [m, n] where 0 <= m <= n <= 2147483647, return the bitwise AND of all numbers in this range, inclusive.

For example, given the range [5, 7], you should return 4.
 * @author user
 *
 */
public class BitWiseAndNumbersRange {
	public static void main(String[] args) {
		int rangeBitwiseAnd = rangeBitwiseAnd(14
				, 12);
		System.out.println(rangeBitwiseAnd);
	}
	public static int rangeBitwiseAnd(int m, int n) {
        int min = Math.min(m,n);
        int max = Math.max(m,n);
        //int msbIdxMax = getMSBIndex(max);
        //int msbIdxMin = getMSBIndex(min);
        if(max == 0 && min == 0) {
            return 0;
        } else {
            int []maxMSB = getOnesMSB(max);
            int []minMSB = getOnesMSB(min);
            System.out.println("Max MSB: "+Arrays.toString(maxMSB));
            System.out.println("Min MSB: "+Arrays.toString(minMSB));
            int msbLeftMaxIdx = maxMSB[0];
            int msbRightMaxIdx = maxMSB[1];
            int msbLeftMinIdx = minMSB[0];
            int msbRightMinIdx = minMSB[1];
            
            if(msbLeftMaxIdx ==  msbLeftMinIdx) {
                int ans = 0;
                for(int i = msbRightMinIdx;i<=msbLeftMinIdx;i++) {
                    ans = ans ^ (1 << i);
                }
                return ans;
            } else {
                return 0;
            }
        }
    }
    
    public static int[] getOnesMSB(int num) {
        int mask = 1;
        int lastSeenOne = -1;
        int endOne = -1;
        boolean isLastSeenZero = true;
        for(int i=0;i<Integer.SIZE-1;i++) {
            if((num & mask) !=0) {
                if(isLastSeenZero) {
                    endOne = lastSeenOne = i;
                } else {
                    lastSeenOne = i;
                }
                //lastSeenOne = i;
                isLastSeenZero = false;
            } else {
                isLastSeenZero = true;
            }
            mask = mask << 1;
        }
        return new int[]{lastSeenOne,endOne};
    }
}
