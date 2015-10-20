package arrays.search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given an integer array of size n, find all elements that appear more than n/3 times.
 *  The algorithm should run in linear time and in O(1) space.
 * 
 * Soln for n=2:http://www.geeksforgeeks.org/majority-element/
 * 
 * Good soln for n=3:* https://leetcode.com/discuss/56737/my-o-n-time-solution-20ms
 * 
 * General soln:https://leetcode.com/discuss/57953/general-solution-searching-elements-that-appear-more-times
 * 
 * Algo:Get integers which occur more than n/k times
 * 1) Such integers can occur minimum k-1 times. So maintain two list for counter and index of size k-1
 * 2) Iterate through array one by one
 * 		a) In each outeriteration, iterate thorugh the 2 list simulataneously(inner iteration).
 * 		b) While iterating though list, If eleItr occurs in list(even if count is 0) or we find a counter == 0, we increment the 
 * 			counter at the position and update index to eleItr's index and break from the inner iteration of lists
 * 		c) If we had not breaken from the inner iteration, it means eleItr is not present in lists. so we decrement
 * 			counters whose counters not equal to zero
 * 3) Now our list contains the possible majority elements. See whether they satisfy the majority condition
 * 
 * Check my working implementation instead of steps a,b,c
 * @author user
 * 
 */
public class GenericMajorityElement {
	
	/**
	 * Get integers which occur more than n/k times
	 * @param nums
	 * @param k
	 * @return
	 */
	public static List<Integer> majorityElement(int[] nums, int k) {
		int maxNoOfMajEles = k-1;
		Integer []posblMajEles = new Integer[maxNoOfMajEles];
		int []count = new int[maxNoOfMajEles];
		for (int i = 0; i < nums.length; i++) {
			boolean isUpdated = false;
			int ele = nums[i];
			for (int j = 0; j < maxNoOfMajEles; j++) {
				if(posblMajEles[j] != null && posblMajEles[j] == ele){
					//ele already exists(even if count is 0). So update count
					count[j]++;
					isUpdated = true;
					posblMajEles[j] = ele;
					break;
				}
			}
			if(!isUpdated) {
				for (int j = 0; j < maxNoOfMajEles; j++) {
					//Element doesnt occur. So try to insert the element at a position where there is empty count.
					if(count[j] == 0) {
						//seperated from previous
						count[j] = 1;
						isUpdated = true;
						posblMajEles[j] = ele;
						break;
					}
				}
			}
			if(!isUpdated) {
				//element doesnt exists and no element with count == 0. So decrement count of all elements.
				//Dont remove the chance of majority element by making it null
				for (int j = 0; j < maxNoOfMajEles; j++) {
					if(count[j] == 1) {
						count[j] = 0;
						//dont  make it null as that element has a chance of occuring
						//posblMajEles[j] = null;
					} else if(count[j] > 1) {
						count[j]--;
					}
					/*if(count[j] == 0 && !isInserted) {
						count[j] = 1;
						posblMajEles[j] = ele;
						isInserted = true;
					}*/
				}
			}
		}
		Arrays.fill(count, 0);
		//check whther the elements are actually majority elements
		for (int i = 0; i < nums.length; i++) {
			for (int j = 0; j < maxNoOfMajEles; j++) {
				if(posblMajEles[j] != null && posblMajEles[j] == nums[i]) {
					count[j]++;
				}
			}
		}
		List<Integer> majEles = new ArrayList<Integer>();
		int threshold = nums.length/k;
		for (int j = 0; j < maxNoOfMajEles; j++) {
			if(count[j] > threshold) {
				majEles.add(posblMajEles[j]);
			}
		}
		return majEles;
	}
	
	public static void main(String[] args) {
		int[] nums = {0,-1,2,-1};
		List<Integer> majorityElement = majorityElement(nums, 3);
		System.out.println(majorityElement);
	}
}
