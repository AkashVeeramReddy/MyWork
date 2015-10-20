package strings;

import java.util.LinkedList;

/**
 * Given two numbers represented as strings, return multiplication of the
 * numbers as a string.
 * 
 * Note: The numbers can be arbitrarily large and are non-negative.
 * https://leetcode.com/problems/multiply-strings/
 * 
 * @author user
 * 
 */
public class MultiplyStrings {
	public static String multiply(String num1, String num2) {
		//String product = "";
		int parseInt = -1;
		try {
			parseInt = Integer.parseInt(num1);
		} catch (NumberFormatException e) {
		}
		int parseInt2 = -1;
		try {
			parseInt2 = Integer.parseInt(num2);
		} catch (NumberFormatException e) {
		}
		if(parseInt == 0 || parseInt2 == 0) {
			return "0";
		} else if(parseInt == 1) {
			return num2;
		} else if(parseInt2 == 1) {
			return num1;
		} else {
			return multiply(num1,num2,num2.length()-1);
		}
		//return product;
    }

	private static String multiply(String num1, String num2, int num2EndIdx) {
		if(num2EndIdx == 0) {
			return multiply(num1, num2.charAt(0)-'0');
		} else {
			String multiply = multiply(num1, num2, num2EndIdx-1);
			if(multiply.equals("0")) {
				multiply = "";
			}
			return add(multiply + "0",multiply(num1, num2.charAt(num2EndIdx)-'0'));
		}
	}
	
	public static String multiply(String num,int digit) {
		if(digit == 0)
			return "0";
		int carry = 0;
		int digitItrNum;
		int productDigit;
		StringBuilder product = new StringBuilder();
		LinkedList<Integer> productList = new LinkedList<Integer>();
		for (int i = num.length()-1; i >=0; i--) {
			digitItrNum = num.charAt(i) - '0';
			productDigit = digitItrNum*digit + carry;
			productList.addFirst(productDigit%10);
			carry = productDigit/10; 
		}
		if(carry != 0) {
			productList.addFirst(carry);
		}
		for (Integer integer : productList) {
			product.append(integer);
		}
		return product.toString();
	}
	
	public static String add(String small,String large) {
		if(small.length() > large.length())
			return add(large, small);
		if(small.equals("0"))
			return large;
		StringBuilder sum = new StringBuilder();
		char[] smallArray = small.toCharArray();
		char[] largeArray = large.toCharArray();
		LinkedList<Integer> sumList = new LinkedList<Integer>();
		int carry = 0,digitSmall,digitLarge,lenSmall = small.length(),lenLarge = large.length(),sumItr;
		for (int i = 0; i < large.length(); i++) {
			int j = lenSmall - i -1;
			digitSmall = ((j < 0) ? 0 : smallArray[j] - '0');
			digitLarge = largeArray[lenLarge - i -1] -'0';
			sumItr = digitSmall + digitLarge + carry;
			sumList.addFirst(sumItr%10);
			carry = sumItr/10;
		}
		if(carry != 0) {
			sumList.addFirst(carry);
		}
		for (Integer integer : sumList) {
			sum.append(integer);
		}
		return sum.toString();
	}
	
	public static void main(String[] args) {
		/*String add1 = "001";
		String add2 = "399";
		String sum = add(add1, add2);*/
		String num = "999";
		String multiply = multiply(num, "99");
		System.out.println(multiply);
	}
	
}
