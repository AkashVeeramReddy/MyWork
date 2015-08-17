package tree.segmenttree;

import java.util.Arrays;

/**
 * https://www.hackerrank.com/contests/codeagon/challenges/sherlock-and-subarray-queries
 * Watson gives to Sherlock an array A1,A2,…,AN of integers. He also gives him M queries of the form Li,Ri. For each query, Sherlock has to find how many times the largest element of the subarray A[Li,Ri] occurs in A[Li,Ri]. 
A[i,j] denotes the subarray Ai,Ai+1...Aj.

Input Format 
First line contains N and M. Next line contains N integers denoting the array A. Each of the next M lines contain two space separated integers denoting Li and Ri.
Sample Input

5 3
3 1 3 2 1
1 3
2 5
1 5
Sample Output

2
1
2
Explanation 
In subarray S[1,3], the largest element is 3 which occurs 2 times. 
In subarray S[2,5], the largest element is 3 which occurs 1 times. 
In subarray S[1,5], the largest element is 3 which occurs 2 times.
 * @author user
 *
 */
public class RangeNoOfMaximum {
	
	
	protected Info[] segmentTree;
	//no of elements(not in segment tree)
	protected int noOfElements;
	protected Integer[] actualArray;
	
	public RangeNoOfMaximum(Integer []array) {
		actualArray = Arrays.copyOf(array, array.length);
		noOfElements = array.length;
		//size reqd is 2*(2 ^ Ceil(log2(n))) - 1
		double logNoOfEle = log2(noOfElements);
		double ceil = Math.ceil(logNoOfEle);
		double pow = Math.pow(2, ceil);
		pow = pow*2;
		segmentTree = new Info[(int)pow - 1];
		populateSegmentTree(array);
	}
	
	protected void populateSegmentTree(Integer[] array) {
		populateSegmentTree(array,0,array.length - 1,0);
	}
	
	protected int getMidPoint(int start,int end) {
		return start + (end - start)/2;
	}
	
	protected int getParentIdx(int idx) {
		return (idx + 1)/2 - 1;
	}
	
	protected int getLeftChildIdx(int idx) {
		return 2*idx + 1;
	}
	
	protected int getRightChildIdx(int idx) {
		return 2*idx + 2;
	}
	
	protected Info populateSegmentTree(Integer[] array, int start, int end,int idx) {
		if(start == end) {
			//leaf
			Info info = new Info();
			info.maximum = array[start];
			info.occurence = 1;
			segmentTree[idx] = info;
			return info;
		} else {
			int mid = getMidPoint(start, end);
			int leftSumIdx = getLeftChildIdx(idx);
			Info leftInfo = populateSegmentTree(array, start, mid, leftSumIdx);
			
			int rightSumIdx = getRightChildIdx(idx);
			Info rightInfo = populateSegmentTree(array, mid+1, end, rightSumIdx);
			
			Info info = new Info();
			if(leftInfo.maximum == rightInfo.maximum) {
				info.maximum = leftInfo.maximum;
				info.occurence = leftInfo.occurence + rightInfo.occurence;
			} else if(leftInfo.maximum < rightInfo.maximum) {
				info.maximum = rightInfo.maximum;
				info.occurence = rightInfo.occurence;
				//return rightInfo;
			} else {
				//leftInfo.maximum > rightInfo.maximum
				info.maximum = leftInfo.maximum;
				info.occurence = leftInfo.occurence;
			}
			segmentTree[idx] = info;
			return info;
		}
	}
	
	@Override
	public String toString() {
		return "Data = " + Arrays.toString(actualArray) + 
				" ST = " + Arrays.toString(segmentTree);
	}
	
	public static double log2(double x) {
	     return Math.log(x)/Math.log(2.0d);
	}
	
	public int findNoOfMaximum(int lower,int higher) {
		//check range
		if(lower >=0 && higher<noOfElements) {
			//return findSum(sumIdx,lower, higher);
			return findNoOfMaximumInRange(0, 0, noOfElements-1, lower, higher).occurence;
		}
		return -1;
	}
	
	public Info findNoOfMaximumInRange(int sumIdx, int lowerForSumIdx,int higherForSumIdx,
			int lower,int higher) {
		if(lowerForSumIdx >= lower && higherForSumIdx <= higher) {
			//within range
			return segmentTree[sumIdx];
		} else {
			//range complete mismatch
			if(lowerForSumIdx > higher || higherForSumIdx < lower) {
				Info info = new Info();
				info.maximum = Integer.MIN_VALUE;
				info.occurence = 0;
				return info;
			} else {
				//some range overlap
				int leftSumIdx = getLeftChildIdx(sumIdx);
				int rightSumIdx = getRightChildIdx(sumIdx);
				int mid = getMidPoint(lowerForSumIdx, higherForSumIdx);
				
				Info leftInfo = findNoOfMaximumInRange(leftSumIdx, lowerForSumIdx, mid, lower, higher);
				Info rightInfo = findNoOfMaximumInRange(rightSumIdx, mid+1, higherForSumIdx, lower, higher);
				
				if(leftInfo.maximum == rightInfo.maximum) {
					Info info = new Info();
					info.maximum = leftInfo.maximum;
					info.occurence = leftInfo.occurence + rightInfo.occurence;
					return info;
				} else if(leftInfo.maximum < rightInfo.maximum) {
					return rightInfo;
				} else {
					//leftInfo.maximum > rightInfo.maximum
					return leftInfo;
				}
			}
			
		}
		
	}
	public class Info {
		int maximum;
		int occurence;
		@Override
		public String toString() {
			return "[max=" + maximum + ",occ=" + occurence
					+ "]";
		}
		
	}
	
}
