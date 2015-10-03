package numbers;

import java.util.Arrays;

/**
 * http://www.geeksforgeeks.org/given-a-number-find-next-smallest-palindrome-
 * larger-than-this-number/
 * 
 * Given a number, find the next smallest palindrome Given a number, find the
 * next smallest palindrome larger than this number. For example, if the input
 * number is “2 3 5 4 5, the output should be “2 3 6 3 2. And if the input
 * number is “9 9 9, the output should be “1 0 0 1".
 * 
 * The input is assumed to be an array. Every entry in array represents a digit
 * in input number. Let the array be ‘num[]’ and size of array be ‘n’
 * 
 * @author user
 * 
 */
public class NextSmallestPalindrome {
	public static void main(String[] args) {
		int []arr = {9,9,9,9};
		int[] nextSmallestPalindrome = getNextSmallestPalindrome(arr);
		System.out.println(Arrays.toString(nextSmallestPalindrome));
	}
	public static int[] getNextSmallestPalindrome(int []arr) {
		int leftItr = 0;
		int rightItr = arr.length-1;
		boolean isIncreaseReqd = false;
		boolean isChangeOcurred = false;
		while(leftItr<=rightItr) {
			if(leftItr == rightItr) {
				if(!isIncreaseReqd && !isChangeOcurred) {
					isIncreaseReqd = true;
				}
				if(isIncreaseReqd) {
					if(arr[leftItr] != 9) {
						arr[leftItr]++;
						isIncreaseReqd = false;
						
					} else {
						arr[leftItr]= 0;
						isIncreaseReqd = true;
						leftItr--;
						rightItr++;
					}
				}
				break;
			} else if(rightItr-leftItr == 1) {
				int leftNo = arr[leftItr];
				int rightNo = arr[rightItr];
				if(leftNo == rightNo) {
					//propogate isIncreaseReqd
					if(!isIncreaseReqd && !isChangeOcurred) {
						isIncreaseReqd = true;
					}
				} else if(leftNo < rightNo) {
					arr[rightItr] = leftNo;
					isIncreaseReqd = true;
					isChangeOcurred = true;
				} else {
					//leftNo > rightNo
					arr[rightItr] = leftNo;
					isIncreaseReqd = false;
					isChangeOcurred = true;
				}
				break;
			} else {
				int leftNo = arr[leftItr];
				int rightNo = arr[rightItr];
				if(leftNo == rightNo) {
					//propogate isIncreaseReqd
				} else if(leftNo < rightNo) {
					arr[rightItr] = leftNo;
					isChangeOcurred = true;
					isIncreaseReqd = true;
				} else {
					//leftNo > rightNo
					arr[rightItr] = leftNo;
					isChangeOcurred = true;
					isIncreaseReqd = false;
				}
				leftItr++;
				rightItr--;
			}
		}
		if(isIncreaseReqd) {
			while(leftItr>=0 && rightItr<arr.length) {
				if(arr[leftItr] == 9) {
					arr[leftItr] = 0;
					arr[rightItr] = 0;
					leftItr--;
					rightItr++;
				} else {
					arr[leftItr]++;
					arr[rightItr]++;
					isIncreaseReqd = false;
					break;
				}
			}
			if(isIncreaseReqd) {
				int []newArr = new int[arr.length+1];
				newArr[0] = 1;
				newArr[arr.length] = 1;
				//rest all 0s as int array always initialized with zeroes
				return newArr;
			}
		}
		return arr;
	}
}
