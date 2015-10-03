package online.codejam.sep15.gnumber;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

import utils.FileUtils;

public class GNumberGame {
	public static final String [] PLAYERS = {"Laurence","Seymour","NULL"};
	public static void main(String[] args) {
		boolean readFromFile = true;
		Scanner scanner = null;
		if(readFromFile) {
			File file = FileUtils.getFile("mine.txt", GNumberGame.class);
			try {
				scanner = new Scanner(file);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		} else {
			scanner = new Scanner(System.in);
		}
		int noCases = scanner.nextInt();
		long num;
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < noCases; i++) {
			num = scanner.nextLong();
			int winner = getWinner(num, 0);
			builder.append("Case #");
			builder.append((i+1));
			builder.append(":");
			builder.append(" ");
			builder.append(PLAYERS[winner]);
			builder.append("\n");
		}
		System.out.println("Done");
		String property = System.getProperty("user.home");
		File homeFolder = new File(property);
		File op = new File(homeFolder,"output.txt");
		try {
			if(!op.exists()) {
				op.createNewFile();
			}
			writeToFile(op, builder.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * @param num 
	 * @param turn = 0 first players turn, 1 second players turn. The guy having the turn
	 * will try to win by getting the other player to have gnumber1
	 * @return
	 */
	public static int getWinner(long num, int turn) {
		int sqrt = (int) Math.pow(num, 0.5);
		//infoArray[0] contains info pertaining to 1
		Info [] infoArray = new Info[sqrt];
		return getWinner(num, turn, infoArray);
	}
	public static int getWinner(long num, int turn,Info [] infoArray) {
		Info info = null;
		if(num <= infoArray.length) {
			int idx = (int) (num -1);
			if(infoArray[idx] == null) {
				info = new Info();
				infoArray[idx] = info;
			} else {
				info = infoArray[idx];
			}
			if(info.isTurnGuyWinner == null) {
				//continue to for loop
			} else {
				//isTurnGuyWinner has been calculated
				return info.isTurnGuyWinner?turn:((turn+1)%2);
			}
		} else {
			//dummy info which cant be memoized
			info = new Info();
		}
		if(num == 1) {
			info.isTurnGuyWinner = false;
			return (turn + 1)%2;
		}
		int sumOfDigits = getSumOfDigits(num, infoArray);
		Info factorsAndPrimeInfo = getFactorsAndPrimeInfo(num, infoArray);
		if(sumOfDigits == num && factorsAndPrimeInfo.isPrime) {
			info.isTurnGuyWinner = false;
			return (turn + 1)%2;
		}
		/*if(factorsAndPrimeInfo.isPrime) {
			//the guy with the current turn wins
			info.isTurnGuyWinner = true;
			return turn;
		}*/ else {
			Map<Long, FactorInfo> factors = factorsAndPrimeInfo.factors;
			Set<Entry<Long,FactorInfo>> entrySet = factors.entrySet();
			for (Entry<Long, FactorInfo> entry : entrySet) {
				FactorInfo value = entry.getValue();
				long dividend = value.dividend;
				System.out.println(dividend);
				int winner = getWinner(dividend, (turn + 1)%2,infoArray);
				if(winner == turn) {
					info.isTurnGuyWinner = true;
					return turn;
				}
			}
		}
		//no way he can win
		info.isTurnGuyWinner = false;
		return (turn + 1)%2;
	}
	
	public static Info getFactorsAndPrimeInfo(long num, Info[] infoArray) {
		if(num == 1) {
			Info info = new Info();
			info.sumOfDigits = 1;
			info.isPrime = true;
			return info;
		}
		Info info = null;
		if(num <= infoArray.length) {
			int idx = (int) (num -1);
			info = infoArray[idx];
			if(info == null) {
				info = new Info();
				infoArray[idx] = info;
			}
			Boolean isPrime = info.isPrime;
			if(isPrime == null) {
				//go to the for loop
			} else {
				return info;
				//return isPrime;
			}
		} else {
			//dummy info
			info = new Info();
		}
		//check for primality
		long sqrt = (long) Math.pow(num, 0.5);
		boolean isPrime = true;
		for (long possFactorItr = 2; possFactorItr <= sqrt; possFactorItr++) {
			Info infoOfPossFactor = getFactorsAndPrimeInfo(possFactorItr, infoArray);
			if(infoOfPossFactor.isPrime) {
				int rem = (int) (num%possFactorItr);
				if(rem == 0) {
					//num is not prime
					isPrime = false;
					long dividend = (num/possFactorItr);
					Info dividendInfo = getFactorsAndPrimeInfo(dividend, infoArray);
					/*if(dividendInfo.isPrime) {
						if(dividend!=possFactorItr) {
							info.addFactor(possFactorItr, dividend);
						} else {
							info.addFactor(possFactorItr, 1);
						}
					} else {*/
						Map<Long, FactorInfo> divFactors = dividendInfo.factors;
						FactorInfo factorInfo = divFactors.get(possFactorItr);
						if(factorInfo == null) {
							info.factors = getCopy(divFactors);
							info.addFactor(possFactorItr, dividend);
						} else {
							//should try to optimize
							info.factors = getCopy(divFactors);
							info.incrementPower(possFactorItr);
						}
						Set<Entry<Long,FactorInfo>> entrySet = info.factors.entrySet();
						for (Entry<Long, FactorInfo> entry : entrySet) {
							Long key = entry.getKey();
							if(key != possFactorItr) {
								FactorInfo value = entry.getValue();
								value.dividend = value.dividend*possFactorItr;
							}
						}
					//}
					break;
				}
			}
		}
		info.isPrime = isPrime;
		if(isPrime) {
			FactorInfo value = new FactorInfo();
			value.power = 1;
			value.dividend = 1;
			info.factors.put(num, value);
		}
		return info;
	}
	private static Map<Long, FactorInfo> getCopy(
			Map<Long, FactorInfo> divFactors) {
		Map<Long, FactorInfo> copy = new HashMap<Long, FactorInfo>();
		Set<Entry<Long,FactorInfo>> entrySet = divFactors.entrySet();
		for (Entry<Long, FactorInfo> entry : entrySet) {
			Long key = entry.getKey();
			FactorInfo value = entry.getValue();
			FactorInfo factorInfoCopy = new FactorInfo(value);
			copy.put(key, factorInfoCopy);
		}
		return copy;
	}
	
	public static class Info {
		public Boolean isTurnGuyWinner = null;
		public Integer sumOfDigits = null;
		public Boolean isPrime = null;
		public Map<Long, FactorInfo> factors = new HashMap<Long,FactorInfo>();
		
		/**
		 * if num corresponding to info is repeateldy divided by factor we get 
		 * dividend
		 * @param factor
		 * @param dividend
		 */
		public void addFactor(long factor, long dividend) {
			FactorInfo factorInfo = factors.get(factor);
			if(factorInfo == null) {
				factorInfo = new FactorInfo();
				factorInfo.primeFactor = factor;
				factors.put(factor, factorInfo);
			}
			factorInfo.power++;
			factorInfo.dividend = dividend;
		}
		
		
		
		public void incrementPower(long factor) {
			FactorInfo factorInfo = factors.get(factor);
			if(factorInfo == null) {
				factorInfo = new FactorInfo();
				factorInfo.primeFactor = factor;
				factors.put(factor, factorInfo);
			}
			factorInfo.power++;
		}



		@Override
		public String toString() {
			return "[isTurnGuyWinner=" + isTurnGuyWinner
					+ ", sumOfDigits=" + sumOfDigits + ", isPrime=" + isPrime
					+ ", factors=" + factors + "]";
		}
	}
	
	public static class FactorInfo {
		//not reqd
		public long primeFactor = -1;
		//not reqd
		public int power = 0;
		/**
		 * if num corresponding to info is repeateldy divided by factor we get dividend
		 */
		public long dividend = -1;
		@Override
		public String toString() {
			return "[div=" + dividend + "pow=" + power+"]";
		}
		
		public FactorInfo() {
		}
		public FactorInfo(FactorInfo factorInfo) {
			this.dividend = factorInfo.dividend;
			this.power = factorInfo.power;
		}
		
		
	}
	
	public static int getSumOfDigits(long num, Info[] infoArray) {
		if(num == 0) {
			return 0;
		}
		Info info = null;
		if(num <= (infoArray.length)) {
			int idx = (int) (num -1);
			info = infoArray[idx];
			if(info == null) {
				info = new Info();
				infoArray[idx] = info;
			}
			Integer sumOfDigits = info.sumOfDigits;
			if(sumOfDigits == null) {
				
			} else {
				return sumOfDigits;
			}
		}
		long newNum = num / 10;
		int lsb = (int) (num%10);
		int sumOfDigits = lsb + getSumOfDigits(newNum, infoArray);
		if(info != null) {
			info.sumOfDigits = sumOfDigits;
		}
		return sumOfDigits;
		
		/*int rem, sum = 0;;
		while(num > 0) {
			int idx = -1;
			Info info = null;
			if(num < (infoArray.length-1)) {
				idx = (int) (num + 1);
				info = infoArray[idx];
				if(info == null) {
					info = new Info();
					infoArray[idx] = null;
				}
				
			}
			rem = (int) (num % 10);
			sum = sum + rem;
			num = num/10;
			
		}*/
	}
	
	public static void writeToFile(File file, String contents) throws Exception {
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter( file));
			writer.write(contents);
		} finally {
			if(writer != null)
				writer.close( );
		}
	}
}
