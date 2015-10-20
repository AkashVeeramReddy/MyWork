package arrays.search;

import sorting.RadixSortStable;

/**
 * Maximum Gap
 * http://cgm.cs.mcgill.ca/~godfried/teaching/dm-reading-assignments/Maximum-Gap-Problem.pdf
 * http://www.programcreek.com/2014/03/leetcode-maximum-gap-java/
 * 
 * https://leetcode.com/problems/maximum-gap/
 * 
 * Given an unsorted array, find the maximum difference between the successive
 * elements in its sorted form.
 * 
 * Try to solve it in linear time/space.
 * 
 * Return 0 if the array contains less than 2 elements.
 * 
 * You may assume all elements in the array are non-negative integers and fit in
 * the 32-bit signed integer range.
 * 
 * My soln not optimal. See below url
 * http://www.careercup.com/question?id=14764703
 * 
 * @author user
 * 
 */
public class MaximumGap {
	public static int maximumGap(int[] nums) {
		if (nums.length < 2)
			return 0;
		nums = RadixSortStable.sortArrContainingPosIntegers(nums, 4);
		int maxDif = 0;
		for (int i = 1; i < nums.length; i++) {
			maxDif = Math.max(maxDif, nums[i] - nums[i - 1]);
		}
		return maxDif;
	}
}
