package arrays.search;

import java.util.HashMap;
import java.util.Map;

/**
 * Longest Consecutive Sequence
 * 
 * https://leetcode.com/problems/longest-consecutive-sequence/
 * 
 * Given an unsorted array of integers, find the length of the longest
 * consecutive elements sequence.
 * 
 * For example, Given [100, 4, 200, 1, 3, 2], The longest consecutive elements
 * sequence is [1, 2, 3, 4]. Return its length: 4.
 * 
 * Your algorithm should run in O(n) complexity.
 * 
 * @author user
 * 
 */
public class LongestConsecutiveNumbers {
	public static int longestConsecutive(int[] nums) {
		int lcs = 0;
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		int itrEle = nums[i];
		for (int i = 0; i < nums.length; i++) {
			//map.containsKey(key);
		}
	}
}
