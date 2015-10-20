package arrays.search;

import java.util.Map.Entry;
import java.util.TreeMap;

/**
 * https://leetcode.com/problems/contains-duplicate-iii/
 * 
 * Contains Duplicate III
 * 
 * Given an array of integers, find out whether there are two distinct indices i
 * and j in the array such that the difference between nums[i] and nums[j] is at
 * most t and the difference between i and j is at most k.
 * 
 * @author user
 * 
 */
public class NearlyDuplicate {
	public static void main(String[] args) {
		int[]arr= new int[]{2,1};
		boolean containsNearbyAlmostDuplicate = containsNearbyAlmostDuplicate(arr, 1, 1);
		System.out.println(containsNearbyAlmostDuplicate);
	}
	public static boolean containsNearbyAlmostDuplicate(int[] nums, int diffBtwIdx, int diffBtwNum) {
        if(nums.length == 0|| nums.length == 1) {
        	return false;
        }
        if(diffBtwIdx == 0) {
        	return diffBtwNum <= 0;
        }
        //return containsNearbyAlmostDuplicate(nums, Math.abs(diffBtwIdx), Math.abs(diffBtwNum), (diffBtwNum*diffBtwIdx>=0));
        return containsNearbyAlmostDuplicate(nums, diffBtwIdx, diffBtwNum, diffBtwIdx>=0,diffBtwNum>=0);
	}
	
	public static boolean containsNearbyAlmostDuplicate(int[] nums, int diffBtwIdx,int diffBtwnNum, 
			boolean startFromLeft,boolean findFloor) {
		int len = nums.length;
		int size = 0;
		size = 0;
		int itrEle;
		//startFromLeft = diffBtwIdx >=0;
		TreeMap<Integer, Integer> keyVsCount = new TreeMap<Integer, Integer>();
		for (int i = 0; i < nums.length; i++) {
			itrEle = startFromLeft?nums[i]:nums[len - 1 -i];
			//if(size < diffBtwIdx) {
				/*if(itrEle > minSoFar && itrEle < maxSoFar) {
					return true;
				}*/
			Entry<Integer, Integer> entry;
			/*if(findFloor) {
				entry = keyVsCount.floorEntry(itrEle);
			} else {
				entry = keyVsCount.ceilingEntry(itrEle);
			}*/
			entry = keyVsCount.ceilingEntry(itrEle - diffBtwnNum);
			if(entry != null) {
				/*if(findFloor) {
					if(itrEle <= (entry.getKey() + diffBtwnNum)) {
						return true;
					}
				} else {*/
					//find ceiling
					//if(itrEle <= (entry.getKey() + diffBtwnNum)) {
						return true;
					//}
				//}
			}
				/*if(entry != null && itrEle <= (entry.getKey() + diffBtwnNum)) {
					//keyVsCount.put(itrEle, 1);
					return true;
				Entry<Integer, Integer> floorEntry = keyVsCount.floorEntry(itrEle);
				if(floorEntry != null && itrEle <= (floorEntry.getKey() + diffBtwnNum)) {
					//keyVsCount.put(itrEle, 1);
					return true;
				} else {*/
				if(size < diffBtwIdx) {
					keyVsCount.put(itrEle, 1);
					size++;
				} else {
					//remove element at diffBtwIdx
					if(startFromLeft) {
						keyVsCount.remove(nums[i - diffBtwIdx]);
					} else {
						keyVsCount.remove(nums[len - 1 -i + diffBtwIdx]);
					}
					keyVsCount.put(itrEle, 1);
				}
				//}
			//}
		}
		return false;
	}
}
