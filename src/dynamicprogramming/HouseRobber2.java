package dynamicprogramming;

/**
 * After robbing those houses on that street, the thief has found himself a new
 * place for his thievery so that he will not get too much attention. This time,
 * all houses at this place are arranged in a circle. That means the first house
 * is the neighbor of the last one. Meanwhile, the security system for these
 * houses remain the same as for those in the previous street.
 * 
 * Given a list of non-negative integers representing the amount of money of
 * each house, determine the maximum amount of money you can rob tonight without
 * alerting the police.
 * 
 * https://leetcode.com/problems/house-robber-ii/
 * 
 * @author user
 * 
 */
public class HouseRobber2 {
	public static int rob(int[] nums) {
        int len = nums.length;
        if(len == 0)
            return 0;
        if(len == 1)
            return nums[0];
        if(len == 2)
            return Math.max(nums[0],nums[1]);
        int profitEnterFirst[] = new int[len];
        int profitLeaveFirst[] = new int[len];
        profitEnterFirst[0] = nums[0];
        profitEnterFirst[1] = nums[0];
        
        profitLeaveFirst[0] = 0;
        profitLeaveFirst[1] = nums[1];
        for(int i=2;i<len;i++) {
            //profit[i] = Math.max(profit[i-1],profit[i-2] + nums[i]);
            if(i == len-1) {
                 profitEnterFirst[i] = profitEnterFirst[i-1];
            } else {
                profitEnterFirst[i] = Math.max(profitEnterFirst[i-1],profitEnterFirst[i-2] + nums[i]);
            }
            profitLeaveFirst[i] = Math.max(profitLeaveFirst[i-1],profitLeaveFirst[i-2] + nums[i]);
        }
        return Math.max(profitEnterFirst[len-1],profitLeaveFirst[len-1]);
    }
}
