package online.hackerrank.dp;

public class SquareSubsequence {
	
	public static void main(String[] args) {
		long noOfSquareSubsequences = getNoOfSquareSubsequences("acaba");
		System.out.println(noOfSquareSubsequences);
	}
	public static long getNoOfSquareSubsequences(String str) {
		char[] array = str.toCharArray();
		long ans = 0;
		int len = array.length;
		Integer [][] info = new Integer[len][len];
		for (int i = 0; i < len; i++) {
			info[i][i] = 0;
		}
		int end;
		int val = 0;
		for(int lengthItr = 2; lengthItr <= len;lengthItr++) {
			for (int start = 0; start <= len - lengthItr; start++) {
				end = start + lengthItr-1;
				if(lengthItr !=2) {
					val = info[start][end-1] + info[start+1][end];
					val = val - info[start+1][end-1];
				}
				if(isEntireStringSquareSequence(array, start, end)) {
					val++;
				}
				if((array[start] == array[end]) && (lengthItr !=2)) {
					val++;
				}
				info[start][end] = val;
			}
		}
		return info[0][len-1];
	}
	
	public static boolean isEntireStringSquareSequence(char []array,int start,int end) {
		int noOfChar = end - start + 1;
		if(noOfChar %2 == 0) {
			int j = noOfChar/2;
			int mid = start + j ;
			for (int i = start; i < mid; i++) {
				if(array[i] != array[i+j]) {
					return false;
				}
			}
			return true;
		}
		return false;
	}
}
