package numbers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import sun.security.action.GetLongAction;

/**
 * https://leetcode.com/problems/largest-number/
 * Given a list of non negative integers, arrange them such that they form the
 * largest number.
 * 
 * For example, given [3, 30, 34, 5, 9], the largest formed number is 9534330.
 * 
 * Note: The result may be very large, so you need to return a string instead of
 * an integer.
 * 
 * http://www.geeksforgeeks.org/given-an-array-of-numbers-arrange-the-numbers-to
 * -form-the-biggest-number/
 * 
 * Arrange given numbers to form the biggest number Given an array of numbers,
 * arrange them in a way that yields the largest value. For example, if the
 * given numbers are {54, 546, 548, 60}, the arrangement 6054854654 gives the
 * largest value. And if the given numbers are {1, 34, 3, 98, 9, 76, 45, 4},
 * then the arrangement 998764543431 gives the largest value.
 * 
 * A simple solution that comes to our mind is to sort all numbers in descending
 * order, but simply sorting doesn’t work. For example, 548 is greater than 60,
 * but in output 60 comes before 548. As a second example, 98 is greater than 9,
 * but 9 comes before 98 in output.
 * 
 * @author user
 * 
 */
public class LargestNumber {
	public static void main(String[] args) {
		int []arr = {3, 30, 34, 5, 9};
		String largestNumber = largestNumber(arr);
		System.out.println(largestNumber);
	}
	public static String largestNumber(int[] nums) {
		List<String> strings = new ArrayList<String>();
        for(int i=0;i<nums.length;i++) {
            strings.add(nums[i] + "");
        }
        boolean isAllStringZero = true;
        for (String string : strings) {
        	isAllStringZero = isAllStringZero && string.equals("0");
		}
        if(isAllStringZero)
        	return "0";
        Collections.sort(strings, new StrComparator());
        StringBuilder builder = new StringBuilder();
        for (String string : strings) {
			builder.append(string);
		}
        return builder.toString();
    }
	
	public static class StrComparator implements Comparator<String>{

		@Override
		public int compare(String o1, String o2) {
			if(o1.equals(o2)) {
				return 0;
			}
			String o1o2 = o1 + o2;
			String o2o1 = o2 + o1;
			return o2o1.compareTo(o1o2);
		}
		
	}
}
