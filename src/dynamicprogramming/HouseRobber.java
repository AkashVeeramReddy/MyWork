package dynamicprogramming;

/**
 * You are a professional robber planning to rob houses along a street. Each
 * house has a certain amount of money stashed, the only constraint stopping you
 * from robbing each of them is that adjacent houses have security system
 * connected and it will automatically contact the police if two adjacent houses
 * were broken into on the same night.
 * 
 * Given a list of non-negative integers representing the amount of money of
 * each house, determine the maximum amount of money you can rob tonight without
 * alerting the police.
 * 
 * https://leetcode.com/problems/house-robber/
 * 
 * @author user
 * 
 */
public class HouseRobber {
	public static int rob(int[] nums) {
        int len = nums.length;
        if(len == 0)
            return 0;
        int profit[] = new int[len];
        if(len > 0) {
            profit[0] = nums[0];
        }
        if(len > 1) {
            profit[1] = Math.max(nums[0],nums[1]);
        }
        for(int i=2;i<len;i++) {
            profit[i] = Math.max(profit[i-1],profit[i-2] + nums[i]);
        }
        return profit[len-1];
    }
}
