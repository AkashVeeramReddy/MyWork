package list.arraylist;

import utils.ArrayUtilities;

public class Sorting {
	public  static <K extends Comparable<? super K>>  void quickSort(K [] array,int b,int e) {
		if(e > b) {
			int part = partition(array,b,e);
			quickSort(array, b, part-1);
			quickSort(array,part+1,e);
		}
	}
	
	private  static <K extends Comparable<? super K>>  int partition(K [] array,int b,int e) {
		int part1 = b;
		int part2 = b;
		K lastEle = array[e];
		for (int itr = b; itr < e; itr++) {
			K itrEle = array[itr];
			int compareTo = itrEle.compareTo(lastEle);
			if(compareTo == 1) {
				part2++;
			} else {
				part1++;
				ArrayUtilities.swapInArray(array, itr, part1-1);
				part2++;
			}
		}
		ArrayUtilities.swapInArray(array, e, part1);
		return part1;
	}
	
}
