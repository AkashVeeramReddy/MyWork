package numbers;
/**
 * 
 * @author user
 * Estimate a value from 2 most adjacent valid values(here ucost >=0 is a valid value).
 * For endpoints- both values on same side
 * for other points - one on left and one on right
 *
 */
public class Interpolate {
	
	public static void main(String[] args) {
		int []amount = new int[]{10,25,50,100,500};
		//int indexLesserThan = getIndexLesserThan(amount, 0);
		String uCost[] = new String[]{"10","20","30","40","50"};
		//System.out.println(indexLesserThan);
		getInterpolatedValue(2000, amount, uCost);
	}
	/**
	 * 
	 * @param n - amount whose cost has to be interpolated.
	 * @param amount - sorted order
	 * @param ucost - values corresponding to sorted order in string
	 * @return - interpolated cost of n
	 */
	public static float getInterpolatedValue(int n, int[] amount, String[] ucost) {
		if(amount.length == 1) {
			return Float.parseFloat(ucost[0]);
		}
		int indexLesserThan = getIndexOfLargestElementLesserThanOrEqualTo(amount, n);
		boolean findStartIdx = true;
		boolean findEndIdx = true;
		if(indexLesserThan == -1) {
			findStartIdx = false;
		}else {
			if(amount[indexLesserThan] == n) { 
				String string = ucost[n];
				Float double1 = Float.parseFloat(string);
				if(double1 > 0) {
					return double1;
				} else {
					if(indexLesserThan == amount.length - 1) {
						findEndIdx = false;
					}
				}
			} else {
				if(indexLesserThan == amount.length - 1) {
					findEndIdx = false;
				}
			}
		}
		int startIdx = 0,endIdx = 0;
		if(findStartIdx) {
			for (int i = indexLesserThan; i>=0; i--) {
				String string = ucost[i];
				Float double1 = Float.parseFloat(string);
				if(double1 > 0) {
					startIdx = i;
					break;
				}
			}
		}
		if(findEndIdx) {
			for (int i = indexLesserThan+1; i<amount.length; i++) {
				String string = ucost[i];
				Float double1 = Float.parseFloat(string);
				if(double1 > 0) {
					endIdx = i;
					break;
				}
			}
		}
		
		if(!findStartIdx) {
			startIdx = endIdx;
			for (int i = startIdx+1; i<amount.length; i++) {
				String string = ucost[i];
				Float double1 = Float.parseFloat(string);
				if(double1 > 0) {
					endIdx = i;
					break;
				}
			}
		}
		if(!findEndIdx){
			endIdx = startIdx;
			for (int i = endIdx-1; i>=0; i--) {
				String string = ucost[i];
				Float double1 = Float.parseFloat(string);
				if(double1 > 0) {
					startIdx = i;
					break;
				}
			}
		}
		
		/*if(findEndIdx && !findStartIdx) {
			//float leftCost = Float.parseFloat(ucost[startIdx]);
			float rightCost = Float.parseFloat(ucost[endIdx]);
			//int interval = amount[endIdx] - amount[startIdx];
			//int leftInterval = n - amount[startIdx];
			int rightInterval =  amount[endIdx] - n;
			//float diffInCost = Math.abs(leftCost - rightCost);
			//float costFromLeft = leftCost + ((float)leftInterval/(float)interval)*diffInCost;
			float costFromRight = rightCost - ((float)rightInterval)*rightCost;
			return costFromRight;
		}
		if(findStartIdx && !findEndIdx) {
			float leftCost = Float.parseFloat(ucost[startIdx]);
			//float rightCost = Float.parseFloat(ucost[endIdx]);
			//int interval = amount[endIdx] - amount[startIdx];
			int leftInterval = n - amount[startIdx];
			//int rightInterval =  amount[endIdx] - n;
			//float diffInCost = Math.abs(leftCost - rightCost);
			float costFromLeft = leftCost + ((float)leftInterval)*leftCost;
			//float costFromRight = rightCost - ((float)rightInterval/(float)interval)*rightCost;
			return costFromLeft;
		}*/
		//if(findStartIdx && findEndIdx) {
			float leftCost = Float.parseFloat(ucost[startIdx]);
			float rightCost = Float.parseFloat(ucost[endIdx]);
			int interval = amount[endIdx] - amount[startIdx];
			int leftInterval = n - amount[startIdx];
			int rightInterval =  amount[endIdx] - n;
			float diffInCost = Math.abs(leftCost - rightCost);
			float costFromLeft = leftCost + ((float)leftInterval/(float)interval)*diffInCost;
			float costFromRight = rightCost - ((float)rightInterval/(float)interval)*diffInCost;
			return costFromRight;
		//}
		//return 0;

    }

    public static int getIndexOfLargestElementLesserThanOrEqualTo(int[] array,int ele) {
        int startIdx = 0;
        int endIdx = array.length-1;;
        int midIdx = (startIdx + endIdx)/2;
        while(startIdx < endIdx) {
        	if(endIdx - startIdx == 1) {
        		if(ele == array[startIdx]) {
        			return startIdx;
        		} else if(ele == array[endIdx]) {
        			return endIdx;
        		}
        		if(ele > array[endIdx]) {
        			return endIdx;
        		} else if(ele < array[startIdx]) {
        			return startIdx - 1;
        		} else {
        			return startIdx;
        		}
        	}
            if(array[midIdx] == ele) {
            	return midIdx;
            } else if(array[midIdx] > ele) {
            	endIdx = midIdx-1;
            } else {
            	startIdx = midIdx + 1;
            }
        }
        if(startIdx == endIdx) {
    		if(array[midIdx] == ele) {
    			return startIdx;
    		} else if(array[midIdx] > ele){
    			return startIdx-1;
    		}
    		return startIdx;
    	}
        return startIdx;
    }
}
