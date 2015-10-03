package arrays.search;

public class SortedArrayFunctions {
	public static int getIndexOfLargestElementLesserThanOrEqualTo(int[] array,int ele) {
        int startIdx = 0;
        int endIdx = array.length-1;;
        int midIdx = (startIdx + endIdx)/2;
        while(startIdx < endIdx) {
        	if(endIdx - startIdx == 1) {
        		if(ele == array[startIdx]) {
        			return startIdx;
        		} else if(ele == array[endIdx]) {
        			return endIdx;
        		}
        		if(ele > array[endIdx]) {
        			return endIdx;
        		} else if(ele < array[startIdx]) {
        			return startIdx - 1;
        		} else {
        			return startIdx;
        		}
        	}
            if(array[midIdx] == ele) {
            	return midIdx;
            } else if(array[midIdx] > ele) {
            	endIdx = midIdx-1;
            } else {
            	startIdx = midIdx + 1;
            }
        }
        if(startIdx == endIdx) {
    		if(array[midIdx] == ele) {
    			return startIdx;
    		} else if(array[midIdx] > ele){
    			return startIdx-1;
    		}
    		return startIdx;
    	}
        return startIdx;
    }
}
