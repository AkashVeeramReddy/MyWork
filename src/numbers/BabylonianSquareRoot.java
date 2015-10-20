package numbers;
/**
 * http://www.geeksforgeeks.org/square-root-of-a-perfect-square/
 * https://leetcode.com/problems/sqrtx/
 * @author user
 *
 */
public class BabylonianSquareRoot {
	public static int mySqrt(int x) {
		Double high = (double) x;
		Double low = 1.0;
        double diff = 0.0001;
        while((high - low) > diff) {
        	high = (high + low)/2;
        	low = x/high;
        }
        int intValue = high.intValue();
        if(x == intValue*intValue) {
        	return intValue;
        }
        return low.intValue();
    }
}
