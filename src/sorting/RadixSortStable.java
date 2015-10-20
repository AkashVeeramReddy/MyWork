package sorting;

import java.util.Arrays;

import utils.ArrayUtilities;

public class RadixSortStable {
	public static int[] sortArrContainingPosIntegers(int []arr, int noOfBitsInGrp) {
		int noOfIter = (int) Math.ceil(Integer.SIZE/noOfBitsInGrp);
		int noOfDistinctVal = (int) Math.pow(2, noOfBitsInGrp);
		int mask = (int) (noOfDistinctVal-1);
		int count[] = new int[noOfDistinctVal];
		int rightShiftsReqdForMask = 0;
		int itrLastBits;
		int []tempStorage = new int[arr.length];
		int []tempVariableForSwap = null;
		for(int i=0;i<noOfIter;i++) {
			//do stable counting sort
			for (int j = 0; j < arr.length; j++) {
				itrLastBits = ((arr[j] & mask)>>rightShiftsReqdForMask);
				count[itrLastBits]++;
			}
			for (int j = 1; j < count.length; j++) {
				count[j] = count[j] + count[j-1];
			}
			for (int j = arr.length-1; j >=0; j--) {
				itrLastBits = ((arr[j] & mask)>>rightShiftsReqdForMask);
				//-1 as array indices start with 0
				tempStorage[count[itrLastBits] -1] = arr[j];
				count[itrLastBits]--;
			}
			tempVariableForSwap = arr;
			arr = tempStorage;
			tempStorage = tempVariableForSwap;
			Arrays.fill(count, 0);
			mask = mask << noOfBitsInGrp;
			rightShiftsReqdForMask = rightShiftsReqdForMask + noOfBitsInGrp;
		}
		return arr;
	}
	
	public static void main(String[] args) {
		int []arr = new int[4096];
		ArrayUtilities.populateIntegerArrayWithRandomNos(arr,16384);
		System.out.println(Arrays.toString(arr));
		sortArrContainingPosIntegers(arr, 4);
		System.out.println(Arrays.toString(arr));
	}
}
