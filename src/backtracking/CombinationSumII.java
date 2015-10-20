package backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a collection of candidate numbers (C) and a target number (T), find all
 * unique combinations in C where the candidate numbers sums to T.
 * 
 * Each number in C may only be used once in the combination.
 * 
 * Note: All numbers (including target) will be positive integers. Elements in a
 * combination (a1, a2, … , ak) must be in non-descending order. (ie, a1 <= a2 <=ak
 * ). The solution set must not contain duplicate combinations. For
 * example, given candidate set {10,1,2,7,6,1,5} and target 8, A solution set
 * is: [1, 7] [1, 2, 5] [2, 6] [1, 1, 6]
 * 
 * https://leetcode.com/problems/combination-sum-ii/
 * 
 * @author user
 * 
 */
public class CombinationSumII {
	public static void main(String[] args) {
		int candidates[] = new int[]{10,1,2,7,6,1,5};
		Arrays.sort(candidates);
		int target = 8;
		List<List<Integer>> combinationSum2 = combinationSum2(candidates, target);
		System.out.println(combinationSum2);
	}
	public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        int size = candidates.length;
        Integer []arr = new Integer[size];
        int candItrIdx = 0;
        int arrItrIdx = 0;
        List<List<Integer>> lists = new ArrayList<List<Integer>>();
        populate(candidates,target,arr,candItrIdx,arrItrIdx,lists);
        return lists;
    }

	private static void populate(int[] candidates, int target, Integer[] arr,
			int candItrIdx, int arrItrIdx, List<List<Integer>> lists) {
		if(arrItrIdx == arr.length || candItrIdx == candidates.length) {
			if(target != 0)
				return;
		}
		if(target == 0) {
			List<Integer> list = new ArrayList<Integer>();
			for (int i = 0; i< arrItrIdx ;i++) {
				list.add(arr[i]);
			}
			lists.add(list);
			return;
		} else if(target < candidates[candItrIdx]) {
			return;
		} else {
			arr[arrItrIdx] = candidates[candItrIdx];
			//number included
			populate(candidates, target - candidates[candItrIdx], arr, candItrIdx+1, arrItrIdx+1, lists);
			//number not included
			populate(candidates, target, arr, candItrIdx+1, arrItrIdx, lists);
		}
	}
}
