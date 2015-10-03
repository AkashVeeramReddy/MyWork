package permutation;

import utils.MyUtilities;

/**
 * http://www.geeksforgeeks.org/generate-all-possible-sorted-arrays-from-alternate-elements-of-two-given-arrays/
 * 
 * Generate all possible sorted arrays from alternate elements of two given sorted arrays
Given two sorted arrays A and B, generate all possible arrays such that first element is 
taken from A then from B then from A and so on in increasing order till the arrays exhausted. 
The generated arrays should end with an element from B.
 * @author user
 *
 */
public class PrintAlternateSorted {
	public static void main(String[] args) {
		int []array1 = new int []{10, 15, 25};
		int []array2 = new int []{1, 5, 20, 30};
		int []print = new int[array1.length + array2.length];
		print(array1,array2,print,0,0,0,true,Integer.MIN_VALUE);
		
	}

	private static void print(int[] array1, int[] array2, int[] print,int array1Idx,int array2Idx,
			int printIdx, boolean nextEleFromFirst,int lastSeen) {
		if(nextEleFromFirst) {
			if(array1Idx < array1.length) {
				int ele = array1[array1Idx];
				if(ele >= lastSeen) {
					if(array1Idx == (array1.length - 1)) {
						//print[printIdx] = array1[array1Idx];
						if(printIdx >= 2 && printIdx%2==0)
							MyUtilities.printIntArrayTill(print, printIdx);
						print[printIdx] = array1[array1Idx];
						print(array1, array2, print, array1Idx+1, array2Idx, printIdx+1, false, array1[array1Idx]);
					} else {
						//considering next ele in array2 after including ele
						print[printIdx] = array1[array1Idx];
						print(array1, array2, print, array1Idx+1, array2Idx, printIdx+1, false, array1[array1Idx]);
						//considering next ele in array1 without including ele
						print(array1, array2, print, array1Idx+1, array2Idx, printIdx, true, lastSeen);
					}
					/*
					//considering next ele in array2 after including ele
					print[printIdx] = array1[array1Idx];
					print(array1, array2, print, array1Idx+1, array2Idx, printIdx+1, false, array1[array1Idx]);
					if(array1Idx != (array1.length - 2)) {
						//considering next ele in array1 without including ele
						print(array1, array2, print, array1Idx+1, array2Idx, printIdx, true, lastSeen);
					} else {
						//print the array
						if(printIdx >= 2 && printIdx%2==0)
							printArrayTill(print, printIdx);
					}
					*/
				} else {
					//ele smaller than lastSeen. So consider next ele in array1
					if(array1Idx == (array1.length - 1)) {
						MyUtilities.printIntArrayTill(print, printIdx);
					} else {
						print(array1, array2, print, array1Idx+1, array2Idx, printIdx, true, lastSeen);
					}
				}
			} else {
				if(printIdx >= 2 && printIdx%2==0)
					MyUtilities.printIntArrayTill(print, printIdx);
			}
		} else {
			if(array2Idx < array2.length) {
				int ele = array2[array2Idx];
				if(ele >= lastSeen) {
					//considering next ele in array2 after including ele
					print[printIdx] = ele;
					print(array1, array2, print, array1Idx, array2Idx+1, printIdx+1, true, ele);
					if(array2Idx != (array2.length - 1)) {
						//considering next ele in array2 without including ele
						print(array1, array2, print, array1Idx, array2Idx+1, printIdx, false, lastSeen);
					} else {
						if(printIdx >= 2 && printIdx%2==0)
							MyUtilities.printIntArrayTill(print, printIdx);
					}
					
				} else {
					//ele smaller than lastSeen. So consider next ele in array2
					print(array1, array2, print, array1Idx, array2Idx+1, printIdx, false, lastSeen);
				}
			} else {
				//print(array1, array2, print, array1Idx, array2Idx, printIdx, true, lastSeen);
			}
			//printArrayTill(print, printIdx-1);
			
			
		}
	}
}
