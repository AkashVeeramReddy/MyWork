package arrays;


public class ArrayFunctionalities {
	
	/**
	 * 
	 * If Integer [] array = {10,11,12,13,4,5,6,7,8,9}
	 * 
	 * @param <K>
	 * @param array - sorted array with a shift
	 * @return index of element 4
	 */
	public static <K extends Comparable<? super K>> int getIndexOfFirstNonSortedElementInSortedRotatedArray(K[] array){
		return getIndexOfFirstNonSortedElementInSortedRotatedArray(array,0,array.length-1);
	}
	
	private static <K extends Comparable<? super K>> int getIndexOfFirstNonSortedElementInSortedRotatedArray(K[] array,int startIdx,int endIdx){
		K startElement = array[startIdx];
		K endElement = array[endIdx];
		if(startIdx == endIdx)
			return -1;
		else if((endIdx - startIdx) == 1) {
			int compareTo = endElement.compareTo(startElement);
			if(compareTo == 1) {
				return -1;
			} else if(compareTo == -1) {
				return endIdx;
			}
		}
		int midIdx = (startIdx + endIdx) /2;
		K midElement = array[midIdx];
		if(startElement.compareTo(midElement) == 1) {
			return getIndexOfFirstNonSortedElementInSortedRotatedArray(array,startIdx,midIdx);
		} else if(midElement.compareTo(endElement) == 1) {
			return getIndexOfFirstNonSortedElementInSortedRotatedArray(array,midIdx,endIdx);
		}
		return -1;
	}
	
	/**
	 * int array[]={-1,-9,8,11,-4};
	 * returns 8 + 11;
	 */
	
	public static int calculateMaxSumOfSubArray(int [] array) {
		return calculateMaxSumOfSubArray(array, 0).maxSumOfArray;
	}
	
	public static InfoForCalculatingMaxSumOfSubArray calculateMaxSumOfSubArray(int [] array,int idx) {
		int noAtIdx = array[idx];
		if(idx == array.length -1) {
			InfoForCalculatingMaxSumOfSubArray infoForEnd = new InfoForCalculatingMaxSumOfSubArray();
			if(noAtIdx > 0) {
				infoForEnd.startIdx = idx;
				infoForEnd.endIdx = idx;
				infoForEnd.maxSumOfArray = noAtIdx;
				infoForEnd.sumFromEndIdx = noAtIdx;
			}
			return infoForEnd;
		}
		InfoForCalculatingMaxSumOfSubArray calculateSize = calculateMaxSumOfSubArray(array,idx+1);
//		calculateSize.sumFromEndIdx = calculateSize.sumFromEndIdx + i;
		if(noAtIdx > 0) {
			/**
			 * sub array whose sum is positive starts from next index
			 */
			if(calculateSize.startIdx == (idx + 1)) {
				calculateSize.startIdx = idx;
				calculateSize.maxSumOfArray = calculateSize.maxSumOfArray + noAtIdx;
				calculateSize.sumFromEndIdx = calculateSize.sumFromEndIdx + noAtIdx;
				return calculateSize;
			}
			/**
			 * Till now there is no sub array whose sum is positive
			 */
			if(calculateSize.maxSumOfArray == 0) {
				calculateSize.maxSumOfArray = calculateSize.sumFromEndIdx = noAtIdx;
				calculateSize.startIdx = calculateSize.endIdx = idx;
				return calculateSize;
			}
			/**
			 * sumOfMiddleElements will be definitely negative
			 */
			int sumOfMiddleElements = calculateSize.sumFromEndIdx - calculateSize.maxSumOfArray;
			if(noAtIdx + sumOfMiddleElements > 0) {
				calculateSize.startIdx = idx;
				calculateSize.sumFromEndIdx = calculateSize.sumFromEndIdx + noAtIdx;
				calculateSize.maxSumOfArray = calculateSize.maxSumOfArray + noAtIdx + sumOfMiddleElements;
				return calculateSize;
			} else if(noAtIdx + sumOfMiddleElements < 0) {
				if(noAtIdx > calculateSize.maxSumOfArray) {
					calculateSize.startIdx = idx;
					calculateSize.endIdx = idx;
					calculateSize.maxSumOfArray = noAtIdx;
					calculateSize.sumFromEndIdx = noAtIdx;
				} else {
					calculateSize.sumFromEndIdx = calculateSize.sumFromEndIdx + noAtIdx;
				}
				return calculateSize;
			}
		} else {
			if(calculateSize.sumFromEndIdx >0) {
				calculateSize.sumFromEndIdx = calculateSize.sumFromEndIdx + noAtIdx;
			}
		}
		return calculateSize;
	}
	
	public static class InfoForCalculatingMaxSumOfSubArray {
		public int startIdx = -1;
		public int endIdx = -1;
		public int sumFromEndIdx = 0;
		public int maxSumOfArray = 0;
	}
	
	public static void main(String[] args) {
		Integer [] array = {0,1,2,3,4,5,6,7,8,9};
		int indexOfFirstNonElement = getIndexOfFirstNonSortedElementInSortedRotatedArray(array);
		System.out.println();
	}
	
}
