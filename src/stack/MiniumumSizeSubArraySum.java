package stack;

import java.util.LinkedList;

/**
 * https://leetcode.com/problems/minimum-size-subarray-sum/
 * 
 * Given an array of n positive integers and a positive integer s, find the
 * minimal length of a subarray of which the sum >= s. If there isn't one, return
 * 0 instead.
 * 
 * For example, given the array [2,3,1,2,4,3] and s = 7, the subarray [4,3] has
 * the minimal length under the problem constraint.
 * 
 * @author user
 * 
 */
public class MiniumumSizeSubArraySum {
	public static void main(String[] args) {
		int[]arr = new int[]{2,3,1,2,4,3};
		int sum = 7;
		int minSubArrayLen = minSubArrayLen(sum, arr);
		System.out.println(minSubArrayLen);
	}
	
	public static int minSubArrayLen(int sum, int[] nums) {
        LinkedList<Integer> idxsQueue = new LinkedList<Integer>();
        int minLength = nums.length + 1;
        int sumOfEleInStack = 0;
        for (int i = 0; i < nums.length; i++) {
        	idxsQueue.add(i);
			sumOfEleInStack = sumOfEleInStack + nums[i];
			while(sumOfEleInStack >= sum) {
				minLength = Math.min(minLength, idxsQueue.size());
				Integer idx = idxsQueue.poll();
				sumOfEleInStack = sumOfEleInStack - nums[idx];
			}
			
		}
        if(minLength == nums.length+1)
        	return 0;
        return minLength;
    }
}
