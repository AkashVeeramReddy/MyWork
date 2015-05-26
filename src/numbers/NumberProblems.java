package numbers;

import java.util.ArrayList;
import java.util.List;

public class NumberProblems {
	
	public static List<Integer> getPrimeFactors(int number) {
		List<Integer> primeFactors = new ArrayList<Integer>();
		List<Integer> primeNumbers = new ArrayList<Integer>();
		int limit = number;
		for (int i = 2; i <= limit; i++) {
			boolean isPrime = true;
			for (Integer integer : primeNumbers) {
				if((i % integer) == 0) {
					isPrime = false;
					break;
				}
			}
			if(isPrime) {
				primeNumbers.add(i);
				int rem = number % i;
				if(rem == 0) {
					System.out.println(i);
					primeFactors.add(i);
					limit = number/i;
				}
			}
		}
		return primeFactors;
	}
	
	public static void main(String[] args) {
		long currentTimeMillis = System.currentTimeMillis();
		List<Integer> primeFactors = getPrimeFactors(45);
		System.out.println("Seconds taken "+((System.currentTimeMillis() - currentTimeMillis)/1000.0));
		System.out.println(primeFactors);
	}
	
}
