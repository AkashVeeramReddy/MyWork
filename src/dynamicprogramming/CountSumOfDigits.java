package dynamicprogramming;

public class CountSumOfDigits {
	
	public static void main(String[] args) {
		long countOfDigitsTill = getCountOfDigitsTill(328);
		System.out.println(countOfDigitsTill);
	}
	public static long getCountOfDigitsTill(int number) {
		long sum = 0;
		//entire value coming after remainder
		int tail = 0;
		int power10 = 1;
		//10^exp10 = power10;
		int exp10 = 0;
		int remainder = 0;
		int sum1 = 0;
		int sum2 = 0;
		int log10 = (int) Math.log10(number);
		//sumOfDigitsInfo[i] will contain sum of digits in numbers from 1 to 10^i
		int sumOfDigitsInfo[] = new int[log10+1];
		sumOfDigitsInfo[0] = 0;
		for (int i = 1; i <= log10; i++) {
			sumOfDigitsInfo[i] = sumOfDigitsInfo[i-1]*10 + 45*power10;
			power10 = power10*10;
		}
		power10 = 1;
		while(number > 0) {
			remainder = number % 10;
			/**
			 * Suppose remainder is 3 while parsing 328
			 * (total sum contributed by 1..2 in its place) + sum of digits coming after
			 * remainder place for 1..2
			 * sum1 = (1 + 2)*100*[sum of digits in 0..100]
			 * sum2 = 3*29 (total sum contributed by 3 in its place) 
			 */
			sum1 = ((remainder-1)*remainder)/2*power10 +
					remainder*(sumOfDigitsInfo[exp10]==0?1:sumOfDigitsInfo[exp10]);
			sum2 = remainder*(tail==0?tail:tail+1);
			sum = sum + sum1 + sum2;
			//for next iter
			tail = remainder*power10 + tail;
			number = number / 10;
			power10 = power10 * 10;
			exp10++;
		}
		return sum;
	}
}
