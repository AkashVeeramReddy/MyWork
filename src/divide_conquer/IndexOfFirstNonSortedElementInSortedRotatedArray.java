package divide_conquer;


public class IndexOfFirstNonSortedElementInSortedRotatedArray {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Integer [] array = {0,1,2,3,4,5,6,7,8,9};
		int indexOfFirstNonElement = getIndexOfFirstNonSortedElementInSortedRotatedArray(array);
		System.out.println();

	}

	/**
	 * 
	 * If Integer [] array = {10,11,12,13,4,5,6,7,8,9}
	 * 
	 * @param <K>
	 * @param array - sorted array with a shift
	 * @return index of element 4
	 */
	public static <K extends Comparable<? super K>> int getIndexOfFirstNonSortedElementInSortedRotatedArray(K[] array){
		return IndexOfFirstNonSortedElementInSortedRotatedArray.getIndexOfFirstNonSortedElementInSortedRotatedArray(array,0,array.length-1);
	}

	public static <K extends Comparable<? super K>> int getIndexOfFirstNonSortedElementInSortedRotatedArray(K[] array,int startIdx,int endIdx){
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

}
