package dynamicprogramming;

import utils.MyUtilities;

/**
 * http://www.geeksforgeeks.org/how-to-print-maximum-number-of-a-using-given-four-keys/
 * 
 * How to print maximum number of A’s using given four keys
This is a famous interview question asked in Google, Paytm and many other company interviews.

Below is the problem statement.

Imagine you have a special keyboard with the following keys: 
Key 1:  Prints 'A' on screen
Key 2: (Ctrl-A): Select screen
Key 3: (Ctrl-C): Copy selection to buffer
Key 4: (Ctrl-V): Print buffer on screen appending it
                 after what has already been printed. 

If you can only press the keyboard for N times (with the above four
keys), write a program to produce maximum numbers of A's. That is to
say, the input parameter is N (No. of keys that you can press), the 
output is M (No. of As that you can produce).
 * @author user
 *
 */
public class PrintMaxChar {
	public static void main(String[] args) {
		int noOfChars = getNoOfChars(20);
		System.out.println(noOfChars);
	}
	
	public static int getNoOfChars(int noOfChars) {
		Integer arr[] = new Integer[noOfChars+1];
		Integer clipboard[] = new Integer[noOfChars+1];
		arr[0] = 0;
		clipboard[0] = 0;
		for(int idx=1;idx<=noOfChars;idx++) {
			int acv = 2*getValAtIdx(arr, idx-3);
			int acvv = 3*getValAtIdx(arr, idx-4);
			int acvvv = 4*getValAtIdx(arr, idx-5);
			int a = getValAtIdx(arr, idx-1) + 1;
			Integer maxElement = MyUtilities.getMaxElement(acv,acvv,acvvv,a);
			arr[idx] = maxElement;
			/*
			if ((maxElement == acv) && (acv > v) && (acv > vv) && (acv > a)) {
				clipboard[idx] =  getValAtIdx(arr, idx-3);
				clipboard[idx-1] = clipboard[idx]; 
			} else {
				clipboard[idx] = clipboard[idx-1]; 
			}
			*/
		}
		return arr[noOfChars];
	}
	
	public static int getValAtIdx(Integer[] arr,int idx) {
		if(idx <0) {
			return 0;
		}
		return arr[idx];
	}
}
