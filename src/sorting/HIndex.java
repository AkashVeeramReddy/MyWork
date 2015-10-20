package sorting;

import java.util.Arrays;

/**
 * H-Index Given an array of citations (each citation is a non-negative integer)
 * of a researcher, write a function to compute the researcher's h-index.
 * 
 * According to the definition of h-index on Wikipedia:
 * "A scientist has index h if h of his/her N papers have at least h citations each, and the other N - h papers have no more than h citations each."
 * 
 * For example, given citations = [3, 0, 6, 1, 5], which means the researcher
 * has 5 papers in total and each of them had received 3, 0, 6, 1, 5 citations
 * respectively. Since the researcher has 3 papers with at least 3 citations
 * each and the remaining two with no more than 3 citations each, his h-index is
 * 3.
 * 
 * Note: If there are several possible values for h, the maximum one is taken as
 * the h-index. 
 * 
 * https://leetcode.com/problems/h-index/
 * 
 * @author user
 * 
 */
public class HIndex {
	public static void main(String[] args) {
		int[] citations = {0};
		int hIndex = hIndex(citations );
		System.out.println(hIndex);
	}
	public static int hIndex(int[] citations) {
		Arrays.sort(citations);
		int length = citations.length;
		if(length == 0)
			return 0;
		int hIndex = -1;
		int table[] = new int[length+1];
		System.out.println(Arrays.toString(citations));
		for (int i = 0; i < citations.length; i++) {
			int no = citations[i];
			if(no < length) {
				table[no]++;
			} else {
				table[length]++;
			}
		}
		System.out.println(Arrays.toString(table));
		int total = 0;
		for (int i = citations.length; i >=0; i--) {
			total = total + table[i];
			table[i] = total;
			if(total >=i) {
				return i;
			}
		}
		System.out.println(Arrays.toString(table));
		return hIndex;
	}
}
