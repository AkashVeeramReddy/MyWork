package gametheory;
import java.util.Scanner;
/**
 * https://www.codechef.com/problems/DIVGAME
 * @author user
 *
 */
public class DivisorGame {
	public static int[] PRIMES;
	public static boolean[] IS_COMPOSITE;
	public static final String[] PLAYERS = new String[]{"Vikram","Betal"}; 

	public static void main(String[] args)  throws Exception {
		boolean readFromFile = true;
		Scanner sc = new Scanner(System.in);
		/*if(readFromFile) {
			File file = FileUtils.getFile("Hai.txt", Hai.class);
			try {
				sc = new Scanner(file);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		} else {
			sc = new Scanner(System.in);
		}*/
		int numTC = sc.nextInt();
		int ele;
		int array[] = new int[numTC];
		int max = 0;
		for (int i = 0; i < numTC; i++) {
			ele =  sc.nextInt();
			array[i] = ele;
			max = Math.max(max, ele);
		}
		initInfo(max);
		for (int i = 0; i < numTC; i++) {
			int winner = getWinner(array[i]);
			System.out.println(PLAYERS[winner]);
		}
	}
	
	public static void initInfo(int maximum) {
		int sqrtN = (int) Math.sqrt(maximum);
		initializeComp(maximum, sqrtN);
		initializePrimes(maximum);
	}

	private static void initializePrimes(int maximum) {
		int primeNo = 0;
		for (boolean b : IS_COMPOSITE) {
			if (!b)
				primeNo++;
		}
		PRIMES = new int[primeNo];
		primeNo = 0;
		for (int p = 2; p < maximum; p++) {
			if (!IS_COMPOSITE[p]) {
				PRIMES[primeNo++] = p;
			}
		}
	}
	/**
	 * Sieve of erastothenes
	 * @param maximum
	 * @param sqrtN
	 */
	private static void initializeComp(int maximum, int sqrtN) {
		IS_COMPOSITE = new boolean[maximum];
		IS_COMPOSITE[0] = IS_COMPOSITE[1] = true;
		for (int p = 2; p <= sqrtN; p++) {
			if (!IS_COMPOSITE[p]) {
				for (int x = 2 * p; x < maximum; x += p) {
					IS_COMPOSITE[x] = true;
				}
			}
		}
	}

	
	
	public static int getWinner(int num) {
		if (num == 2 || num == 17) {
			return 0;
		} else if (num == 1 || num == 16 || num == 34 || num == 289) {
			return 1;
		} else {
			boolean prime = isPrime(num);
			if(prime) {
				return 1;
			} else {
				return 0;
			}
		}
	}

	public static boolean isPrime(int N) {
		for (int prime : PRIMES) {
			if (N == prime) {
				return true;
			} else if (N % prime == 0) {
				return false;
			}
		}
		return true;
	}

}

