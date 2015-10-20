package sorting;

import java.util.Arrays;

import utils.ArrayUtilities;

public class CountingSortUnstable {
	public static void main(String[] args) {
		int []num = new int[20];
		ArrayUtilities.populateIntegerArrayWithRandomNos(num,100);
		System.out.println(Arrays.toString(num));
		countSortUnstable(num, 100);
		System.out.println(Arrays.toString(num));
	}
	
	/**
	 * 
	 * @param num
	 * @param range - nums are in the range 1..n. 0..n also works
	 */
	public static void countSortUnstable(int []num, int range) {
		int []count = new int[range+1];
		for (int i = 0; i < num.length; i++) {
			count[num[i]]++;
		}
		int startItr = 0;
		for (int i = 0; i < count.length; i++) {
			for (int j = 0; j < count[i]; j++) {
				num[startItr] = i;
				startItr++;
			}
		}
	}
}
