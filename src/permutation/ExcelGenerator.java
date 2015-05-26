package permutation;

import java.util.LinkedList;
import java.util.List;


public class ExcelGenerator {
	
	private static void printExcelHeaders(int number) {
		if(number <=0)
			return ;
		List<String> previousHeaders = new LinkedList<String>();
		previousHeaders.add("");
		List<String> currentHeaders = new LinkedList<String>();
		int numberItr = 0;
		boolean isListNeeded = false;
		long lastPowerOf26 = 1;
		long nextPowerOf26 = 0;
		long sumOfAllPowers = 0;
		while(true) {
			nextPowerOf26 = lastPowerOf26*26;
			sumOfAllPowers = sumOfAllPowers + nextPowerOf26;
			isListNeeded = (sumOfAllPowers  < number);
			for(char j = 'A';j<='Z';j++) {
				for (String string : previousHeaders) {
					String strLengthOfI = j + string;
					if(isListNeeded)
						currentHeaders.add(strLengthOfI);
					System.out.print(strLengthOfI);
					System.out.print(",");
					numberItr++;
					if(numberItr >= number)
						return;
				}
				System.out.println();
			}
			previousHeaders = currentHeaders;
			currentHeaders = new LinkedList<String>();
			lastPowerOf26 = nextPowerOf26;
		}
	}
	
	public static void main(String[] args) {
		long currentTimeMillis = System.currentTimeMillis();
		printExcelHeaders(10);
        long currentTimeMillis2 = System.currentTimeMillis();
        System.out.println(currentTimeMillis2 - currentTimeMillis);
	}
}
