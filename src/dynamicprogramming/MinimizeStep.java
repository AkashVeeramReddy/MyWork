package dynamicprogramming;

/**
 * 
 * //Problem Statement 
//One can perform following operation on a number until it becomes 0
//n = n-1 
//n = n/2 (only if its divisible by 2)
//n = n/3 (only if its divisible by 3)
 * 
 * Objective is to minimize the no of steps

 * @author KH348
 *
 */
public class MinimizeStep {
	private static void minimizeStep(int number) {
		Info[] infoArray = new Info[number+1];
		infoArray[0] = new Info();
		for(int i=1;i<=number;i++) {
			Info info = new Info();
			infoArray[i] = info;
			int optimalMode;
			int optimalResult;
			int optimalCost;
			int costBy2 = Integer.MAX_VALUE;
			int costBy3 = Integer.MAX_VALUE;
			int costMinus1 = Integer.MAX_VALUE;
			int resultBy2 = 0;
			int resultBy3 = 0;
			int resultMinusOne = i -1;
			costMinus1 = infoArray[resultMinusOne].cost;
			if(i %2 == 0) {
				resultBy2 = i/2;
				costBy2 = infoArray[resultBy2].cost;
			}
			if(i %3 ==0 ) {
				resultBy3 = i/3;
				costBy3 = infoArray[resultBy3].cost;
			}
			if(costBy2 < costBy3) {
				if(costBy2 < costMinus1) {
					optimalMode = 1;
					optimalResult = resultBy2;
					optimalCost = costBy2;
				} else {
					optimalMode = 0;
					optimalResult = resultMinusOne;
					optimalCost = costMinus1;
				}
			} else {
				if(costBy3 < costMinus1) {
					optimalMode = 2;
					optimalResult = resultBy3;
					optimalCost = costBy3;
				} else {
					optimalMode = 0;
					optimalResult = resultMinusOne;
					optimalCost = costMinus1;
				}
			}
			info.mode = optimalMode;
			info.result = optimalResult;
			info.cost = optimalCost + 1;
		}
		/*System.out.println("==============Optimal Cost Array======================");
		MyUtilities.printSingleDimensionArray(infoArray);*/
		System.out.println("Minimum cost for steps of " + number + " is "+infoArray[number].cost);
		printOptimalCost(infoArray,number);
	}
	private static void printOptimalCost(Info[] infoArray, int index) {
		int mode = infoArray[index].mode;
		int result = infoArray[index].result;
		if(mode != -1) {
			switch (mode) {
			case 0:
				System.out.println("Subtract "+index +" by 1 to get "+result);
				break;
			case 1:
			case 2:
				System.out.println("Divide "  + index + " by "+(mode+1) +" to get "+result);
				break;
			default:
				break;
			}
			printOptimalCost(infoArray, infoArray[index].result);
		}
	}
	public static void main(String[] args) {
		minimizeStep(5);
	}
	
	private static class Info {
		//0 means subtracted by -1,1 means divided by 2,2 means divided by 3
		private int mode = -1;
		private int cost = 0;
		private int result = 0;
		@Override
		public String toString() {
			return "[m=" + mode + ",c=" + cost + ",r="
					+ result + "]";
		}
		
		
	}
	
}
