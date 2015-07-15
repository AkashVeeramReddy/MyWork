package arrays;
/**
 * http://www.geeksforgeeks.org/find-a-tour-that-visits-all-stations/
 * Find the first circular tour that visits all petrol pumps
Suppose there is a circle. There are n petrol pumps on that circle. You are given two sets of data.

1. The amount of petrol that petrol pump will give.
2. Distance from that petrol pump to the next petrol pump.

Calculate the first point from where a truck will be able to complete the circle
 (The truck will stop at each petrol pump and it has infinite capacity). 
 Expected time complexity is O(n). Assume for 1 litre petrol, the truck can go 1 unit of distance.

For example, let there be 4 petrol pumps with amount of petrol and distance to next petrol pump value pairs
 as {4, 6}, {6, 5}, {7, 3} and {4, 5}. The first point from where truck can make a circular tour is 2nd petrol pump.
  Output should be start = 1 (index of 2nd petrol pump).
 * @author user
 *
 */
public class FirstPetrolPump {
	
	
	public static int getIndexOfFirstPetrolPump(Integer[] petrol,Integer []distanceToNext) {
		int size = petrol.length - 1;
		int startIdx = 0;
		int endIdx = 0;
		int petrolLeft = 0;
		
		//int newEndIdx = increment(endIdx, size);
		while(true) {
			if(petrolLeft + petrol[endIdx] >= distanceToNext[endIdx]) {
				petrolLeft = petrolLeft + petrol[endIdx] - distanceToNext[endIdx];
				endIdx = increment(endIdx,size);
				if(startIdx == endIdx) {
					return startIdx;
				}
			} else {
				petrolLeft = petrolLeft - (petrol[startIdx] - distanceToNext[startIdx]);
				startIdx = increment(startIdx,size);
				if(startIdx == 0) {
					return -1;
				}
			}
		}
	}
	
	public static int increment(int i,int noOfPumps) {
		return (i + 1)%noOfPumps;
	}
	
	public static int decrement(int i,int noOfPumps) {
		return (i + noOfPumps - 1)%noOfPumps;
	}
	
	public static void main(String[] args) {
		Integer petrol[] = new Integer[]{4,6,7,4};
		Integer []distanceToNext = new Integer[]{6,5,3,5};
		int indexOfFirstPetrolPump = getIndexOfFirstPetrolPump(petrol, distanceToNext);
		System.out.println(indexOfFirstPetrolPump);
	}
	
}
