package dynamicprogramming;
/**
 * http://www.geeksforgeeks.org/maximum-weight-transformation-of-a-given-string/
 * 
 * Given a string consisting of only A’s and B’s. We can transform the given string to another string by toggling
 *  any character. Thus many transformations of the given string are possible. The task is to find Weight of the
 *   maximum weight transformation.

Weight of a sting is calculated using below formula.


Weight of string = Weight of total pairs + 
                   weight of single characters - 
                   Total number of toggles.

Two consecutive characters are considered as pair only if they 
are different. 
Weight of a single pair (both character are different) = 4
Weight of a single character = 1 
 * @author user
 *
 */
public class MaxWeightTransformationString {
	public static void main(String[] args) {
		printMaximumWeightTransformation("AAAAABB");
	}
	
	public static void printMaximumWeightTransformation(String str) {
		int length = str.length();
		Info infoArray[] = new Info[length];
		infoArray[0] = new Info();
		infoArray[0].maxWeight = 1;

		for (int i = 1; i < length; i++) {
			Info info = new Info();
			info.maxWeight = 1;
			infoArray[i] = info;
			if(i == 1) {
				if(str.charAt(0) == str.charAt(1)) {
					info.mode = 2;
					info.maxWeight = 3;
					info.isToggle = true;
				} else {
					info.mode = 2;
					info.maxWeight = 4;
					info.isToggle = false;
				}
			} else {
				if(str.charAt(i) == str.charAt(i-1)) {
					//toggle
					int maxWtToggle = infoArray[i-2].maxWeight + 3;
					//no toggle
					int maxWtNoToggle = infoArray[i-1].maxWeight + 1;
					if(maxWtNoToggle > maxWtToggle) {
						info.mode = 1;
						info.maxWeight = maxWtNoToggle;
						info.isToggle = false;
					} else {
						info.mode = 2;
						info.maxWeight = maxWtToggle;
						info.isToggle = true;
					}
				} else {
					//toggle
					int maxWtFromPrevPrev = infoArray[i-2].maxWeight + 4;
					//no toggle
					int maxWtFromPrev = infoArray[i-1].maxWeight + 1;
					if(maxWtFromPrevPrev > maxWtFromPrev) {
						info.maxWeight = maxWtFromPrevPrev;
						info.isToggle = false;
						info.mode = 2;
					} else {
						info.maxWeight = maxWtFromPrev;
						info.isToggle = false;
						info.mode = 1;
					}
				}
			}
			
		}
		System.out.println(infoArray[length-1].maxWeight);
	}
	
	public static class Info {
		int maxWeight = 0;
		//mode = 1 came from prev, 2 came from prev's prev
		int mode = 0;
		boolean isToggle = false;
	}
}
