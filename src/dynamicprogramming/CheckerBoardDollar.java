package dynamicprogramming;

public class CheckerBoardDollar {
	
	public static int INFO_ELEMENT_LENGTH = 20;
	
	/**
	 * A checker board has n rows and n columns.
	 * Checker can either 
	 * 1) move up one
	 * 2) move up one and move right one
	 * 3) move up one and move left one
	 * 
	 * <p>
	 * As you move you
	 * will get the dollars in the destination. So if you move up one and move
	 * right one you will only get the dollar in the right checker box above it.
	 * Starting dollar is the dollar present on the column u started
	 * 
	 * <p>
	 * Goal is to maximise dollars
	 * @param n - nxn checker board
	 */
	public static void maximiseCheckerBox(int n) {
		int [][] dollarArray = new int[n][n];
		/*for (int i = 0; i < n; i++) {
			dollarArray[0][i] = 0;
		}*/
		for (int i = 0; i < n; i++) {
			for(int j=0;j<n;j++)
				dollarArray[i][j] = (int) (Math.random() * 10);
		}
		System.out.println("============Dollar Array====================");
		for (int i = n-1; i >= 0; i--) {
			for(int j=0;j<n;j++) {
				System.out.print(dollarArray[i][j]);
				System.out.print("\t");
			}
			System.out.println();
		}
		System.out.println();
		
		Info [][] info = new Info[n][n];
		for (int i = 0; i < n; i++) {
			Info newInfo = new Info();
			newInfo.sum = dollarArray[0][i];
			info[0][i] = newInfo;
		}
		for (int i = 1; i < n; i++) {
			for(int j=0;j<n;j++) {
				Info newInfo = new Info();
				info[i][j] = newInfo;
				int direction = 1;
				int prevIndex = j;
				int sumByLeft = 0,sumByUp = 0,sumByRight = 0;
				sumByUp = info[i-1][j].sum  + dollarArray[i][j];
				if(j != n-1) {
					sumByLeft = info[i-1][j+1].sum  + dollarArray[i][j];
				}
				if(j != 0) {
					sumByRight = info[i-1][j-1].sum  + dollarArray[i][j];
				}
				int sum = sumByUp;
				if(sumByLeft > sumByUp) {
					direction = 0;
					prevIndex = j+1;
					sum = sumByLeft;
					if(sumByLeft < sumByRight) {
						direction = 2;
						prevIndex = j-1;
						sum = sumByRight;
					}
				} else {
					if(sumByUp < sumByRight) {
						direction = 2;
						prevIndex = j-1;
						sum = sumByRight;
					}
				}
				newInfo.direction = direction;
				newInfo.prevIndex = prevIndex;
				newInfo.sum = sum;
			}
		}
		System.out.println("============Info Array====================");
		for (int i = n-1; i >= 0; i--) {
			for(int j=0;j<n;j++) {
				Info obj = info[i][j];
				String string = obj.toString();
				System.out.print(string);
				for(int k = INFO_ELEMENT_LENGTH;k >= string.length();k--) {
					System.out.print(" ");
				}
			}
			System.out.println();
		}
		//print movements of checker
		System.out.println("============Checker Movements====================");
		int maxSum = info[n-1][0].sum;
		int idx = 0;
		for(int i =1;i<n;i++) {
			int sum = info[n-1][i].sum;
			if(sum > maxSum) {
				maxSum = sum;
				idx = i;
			}
		}
		print(info, n-1, idx);
//		System.out.println("Move to row " + (n-1)+ ",column "+idx);
	}
	
	public static void print(Info [][] info,int row,int column) {
		if(row == 0) {
			System.out.println("Start from column "+column);
			return;
		}
		System.out.println("Move to row " +row+ ",column "+column);
		print(info, row-1, info[row][column].prevIndex);
	}
	
	public static void main(String[] args) {
		maximiseCheckerBox(4);
	}
	
	private static class Info {
		private int sum = 0;
		/**
		 * 0 means top left
		 * 1 means top
		 * 2 means top right
		 */
		private int direction = -1;
		private int prevIndex = -1;
		
		@Override
		public String toString() {
			return "[s=" + sum + ", d=" + direction
					+ ", pI=" + prevIndex + "]";
		}
		
		
		
	}
	
}
