package arrays.search;

/**
 * Find the Duplicate Number
 * https://leetcode.com/problems/find-the-duplicate-number/
 * 
 * Given an array nums containing n + 1 integers where each integer is between 1
 * and n (inclusive), prove that at least one duplicate number must exist.
 * Assume that there is only one duplicate number, find the duplicate one.
 * 
 * Note: You must not modify the array (assume the array is read only). You must
 * use only constant, O(1) extra space. Your runtime complexity should be less
 * than O(n2). There is only one duplicate number in the array, but it could be
 * repeated more than once.
 * 
 * @author user
 * 
 */
public class FindDuplicateNumber {
	public static void main(String[] args) {
		int[]arr= {8,1,1,1,2,7,4,3,1,1};
		int findDuplicate = findDuplicate(arr);
		System.out.println(findDuplicate);
	}
	public static int findDuplicate(int[] nums) {
		return findDuplicate(nums,1,nums.length-1);
	}

	private static int findDuplicate(int[] nums, int startPossVal, int endPossVal) {
		if(startPossVal == endPossVal) {
			return startPossVal;
		} else {
			int mid = (startPossVal + endPossVal)/2;
			//check whether mid is a duplicate. If not find which way to go
			int noOfMid = 0;
			int noOfEleGr8rThanMid = 0;
			int noOfEleSmallerThanMid = 0;
			int itrEle;
			for (int i = 0; i < nums.length; i++) {
				itrEle = nums[i];
				if(itrEle == mid) {
					noOfMid++;
				} else if(itrEle < mid && itrEle>=startPossVal) {
					noOfEleSmallerThanMid++;
				} else if(itrEle > mid && itrEle<=endPossVal){
					//itrEle > mid
					noOfEleGr8rThanMid++;
				}
			}
			if(noOfMid > 1)
				return mid;
			if((endPossVal - startPossVal) == 1) {
				//size 2 and since a guaranteed sol exists. the other ele is the one
				return startPossVal + endPossVal - mid;
			}
			if(noOfEleGr8rThanMid > (endPossVal - mid)) {
				//noOfEleGr8rThanMid > no of elements who are supposed to be greater than mid
				return findDuplicate(nums, mid+1, endPossVal);
			} else 
				return findDuplicate(nums, startPossVal, mid-1);
		}
	}
}
