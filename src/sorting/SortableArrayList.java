package sorting;

import list.arraylist.MyArrayList;
import utils.MyUtilities;

public class SortableArrayList<K extends Comparable<? super K>> extends MyArrayList<K> {
	
	
	@SuppressWarnings("unchecked")
	public void insertionSort() {
		for (int i = 1; i < noOfElements; i++) {
			int j = i;
			while (j >= 1) {
				K currentElement = (K) data[j];
				K prevElement = (K) data[j-1];
				if(currentElement.compareTo(prevElement) == -1) {
					MyUtilities.swap(data, j, j-1);
				} else {
					break;
				}
				j--;
			}
		}
	}
	
	public void mergeSort() {
		mergeSort(data, 0, noOfElements -1);
	}
	
	private void mergeSort(Object[] array,int start,int end) {
		if(start < end) {
			int mid = (start + end)/2;
			mergeSort(array, start, mid);
			mergeSort(array, mid + 1, end);
			merge(array,start,mid,end);
		}
	}
	
	public void recursiveInsertionSort() {
		if(noOfElements > 0) {
			recursiveInsertionSort(0);
		}
	}
	
	@SuppressWarnings("unchecked")
	private void recursiveInsertionSort(int index) {
		if(index < noOfElements) {
			K idxElement = (K) data[index];
			int toBeReplacedIndex = index;
			recursiveInsertionSort(index + 1);
			for(int i = index + 1;i<noOfElements;i++) {
				if(idxElement.compareTo((K) data[i]) == 1) {
					data[i-1] = data[i];
					toBeReplacedIndex = i;
				} else {
//					array[i-1] = idxElement;
					break;
				}
			}
			if(toBeReplacedIndex != index) {
				data[toBeReplacedIndex] = idxElement;
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	private void merge(Object[] array, int start, int mid, int end) {
		int combinedLength = end - start + 1;
		int leftItr = start;
		int rightItr = mid + 1;
		Object[] newArray = new Object[combinedLength];
		for (int i = 0; i < combinedLength; i++) {
			if(leftItr > mid) {
				System.arraycopy(array, rightItr, newArray, i, end - rightItr + 1);
				break;
			} else if(rightItr > end){
				System.arraycopy(array, leftItr, newArray, i, mid - leftItr + 1);
				break;
			}
			K leftArrayElement = (K) array[leftItr];
			K rightArrayElement = (K) array[rightItr];
			int compareTo = leftArrayElement.compareTo(rightArrayElement);
			if(compareTo == 0) {
				newArray[i] = leftArrayElement;
				i++;
				newArray[i] = rightArrayElement;
				leftItr++;
				rightItr++;
			} else if(compareTo == -1) {
				newArray[i] = leftArrayElement;
				leftItr++;
			} else if(compareTo == 1) {
				System.out.println(leftArrayElement+","+rightArrayElement);
				newArray[i] = rightArrayElement;
				rightItr++;
			}
		}
		System.arraycopy(newArray, 0, array, start, combinedLength);
		
	}
	
	public static <T extends Comparable<? super T>> void recursiveInsertionSort(T[] array) {
		if(isNonEmptyArray(array)) {
			recursiveInsertionSort(array,0);
		}
	}
	
	public static <T extends Comparable<? super T>> void recursiveInsertionSort(T[] array,int index) {
		if(index < array.length) {
			T idxElement = array[index];
			int toBeReplacedIndex = index;
			recursiveInsertionSort(array, index + 1);
			for(int i = index + 1;i<array.length;i++) {
				if(idxElement.compareTo(array[i]) == 1) {
					array[i-1] = array[i];
					toBeReplacedIndex = i;
				} else {
//					array[i-1] = idxElement;
					break;
				}
			}
			if(toBeReplacedIndex != index) {
				array[toBeReplacedIndex] = idxElement;
			}
		}
	}
	
	public static <K> boolean isNonEmptyArray(K[] array) {
		if(array != null && array.length > 0) {
			return true;
		}
		return false;
	}
	
	public static void main(String[] args) {
		Integer[] array = {9,2,1,8,6,4,7,3};
		recursiveInsertionSort(array);
		System.out.println();
	}
	
}
