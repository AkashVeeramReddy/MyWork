package bitmanip;

public class CommonBitFns {
	
	public static void main(String[] args) {
		/*boolean kthBitSetOrNot = isKthBitSetOrNot(8, 3);
		System.out.println(kthBitSetOrNot);
		kthBitSetOrNot = isKthBitSetOrNot(7, 3);
		System.out.println(kthBitSetOrNot);*/
		/*int num = 7;
		int addOne = addTwoNumber(3, 5);
		System.out.println(addOne);*/
		/*String binaryString = Integer.toBinaryString(num);
		System.out.println(binaryString);
		int rev = reverseBits(num);
		binaryString = Integer.toBinaryString(rev);
		System.out.println(binaryString);*/
		//int arr[] = {12, 1, 12, 3, 12, 1, 1, 2, 3, 3};
		int findUniqueElement = getSquare(3);
		System.out.println(findUniqueElement);
	}
	
	public static boolean isKthBitSetOrNot(int number, int k) {
		int mask = 1 << k;
		return ((number & mask) == mask);
	}
	
	public static int setKthBit(int number, int k) {
		int mask = 1 << k;
		return number | mask;
	}
	
	public static int clearKthBit(int number, int k) {
		int mask = ~(1 << k);
		return number & mask;
	}
	
	public static int toggleKthBit(int number, int k) {
		int mask = (1 << k);
		return number ^ mask;
	}
	
	public static int reverseBits(int number) {
		int output = 0;
		int pos = 0;
		int mask = 1;
		int val = 0;
		while(mask != 0) {
			val = number & mask;
			if(val != 0) {
				output = output |(1 << 31 - pos);
			}
			mask = mask << 1;
			pos++;
		}
		return output;
	}
	/**
	 * http://www.geeksforgeeks.org/next-power-of-2/
	 * 
	 * Write a function that, for a given no n, 
	 * finds a number p which is greater than or equal to n and is a power of 2.
	 * @param number
	 * @return
	 */
	public static int getNextPowerOf2(int number) {
		
		int mask = 1;
		int lastSeen = -1;
		int secndLastSeen = -1;
		int val = 0;
		int pos = 0;
		while(mask != 0) {
			val = (number & mask);
			if(val != 0) {
				secndLastSeen = lastSeen;
				lastSeen = pos;
			}
			mask = mask << 1;
			pos++;
		}
		if(number == 0) {
			return 1;
		}
		if(secndLastSeen == -1) {
			return number;
		} else {
			return 1 << (lastSeen+1);
		}
	}
	
	/**
	 * http://www.geeksforgeeks.org/add-1-to-a-given-number/
	 * Write a program to add one to a given number.
	 *  You are not allowed to use operators like ‘+’, ‘-‘, ‘*’, ‘/’, ‘++’, ‘–‘ …etc.
	 * @param no
	 * @return
	 */
	public static int addOne(int no) {
		int mask = 1;
		int res;
		while(mask > 0) {
			res = mask & no;
			no = no ^ mask;
			if(res == 0) {
				//bit is 0
				break;
			}
			mask = mask << 1;
		}
		return no;
	}
	
	public static int addTwoNumber(int num1, int num2) {
		int noOfBits = Integer.SIZE;
		int mask = 1;
		int carry = 0;
		int sum = 0;
		for(int i = 0;i < noOfBits; i++) {
			int bit1 = (mask & num1)==0?0:1;
			int bit2 = (mask & num2)==0?0:1;
			int sumBit = bit1 ^ bit2 ^ carry;
			if(sumBit != 0)
				sum = sum | mask;
			carry = (bit1 & bit2) | (carry & bit1) | (carry & bit2);
			mask = mask << 1;
		}
		return sum;
	}
	
	public static int findUniqueElement(int [] array) {
		int mask = 1;
		int size = Integer.SIZE;
		int uniqEle = 0;
		for (int i = 0; i < size ; i++) {
			int noOfOnes = 0;
			for (int ele : array) {
				
				int andRes = ele & mask;
				if(andRes != 0) {
					noOfOnes++;
				}
			}
			if(noOfOnes % 3 != 0) {
				uniqEle = uniqEle ^ mask;
			}
			mask = mask << 1;
		}
		return uniqEle;
	}
	
	public static int getSquare(int num) {
		int square = 0;
		int mask = 1;
		int numItr = num;
		//i
		for (int i = 0; i < Integer.SIZE; i++) {
			int val = num & mask;
			if(val != 0) {
				square = square + numItr;
			}
			mask = mask << 1;
			numItr = numItr << 1;
		}
		return square;
	}
}