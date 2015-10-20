package stack;

import java.util.LinkedList;

/**
 * Largest Rectangular Area in a Histogram | Set 2
Find the largest rectangular area possible in a given histogram where the largest 
rectangle can be made of a number of contiguous bars. For simplicity, assume that
 all bars have same width and the width is 1 unit.
 * http://www.geeksforgeeks.org/largest-rectangle-under-histogram/
 * 
 * Working Code
 * @author user
 *
 */
public class LargestHistogram2 {
	public static void main(String[] args) {
		int hist[] = {5,4,5};
		long maxArea = getMaxArea(hist);
		System.out.println(maxArea);
	}
	public static long getMaxArea(int []heights) {
		LinkedList<Integer> idxIncHeight = new LinkedList<Integer>();
		long maxArea = 0;
		int ele;
		for (int i = 0; i < heights.length; i++) {
			ele = heights[i];
			if(idxIncHeight.isEmpty()) {
				idxIncHeight.add(i);
			} else {
				while (!idxIncHeight.isEmpty() && ele < heights[idxIncHeight.getLast()] ) {
					//calculate area which has this bar as miniumum
					Integer idxToCalculateArea = idxIncHeight.removeLast();
					int heightOfBar =heights[idxToCalculateArea];
					int width = 0;
					if(idxIncHeight.isEmpty()) {
						//this block is the smallest so far
						width = i;
					} else {
						width = i - idxIncHeight.getLast() - 1;
					}
					maxArea = Math.max(maxArea, heightOfBar*width);
				}
				idxIncHeight.add(i);
			}
		}
		//stack can be non empty
		while(!idxIncHeight.isEmpty()) {
			//calculate area which has this bar as miniumum
			Integer idxToCalculateArea = idxIncHeight.removeLast();
			int heightOfBar =heights[idxToCalculateArea];
			int width = 0;
			if(idxIncHeight.isEmpty()) {
				//this block is the smallest
				width = heights.length;
			} else {
				width =  heights.length - idxIncHeight.getLast() - 1;
			}
			maxArea = Math.max(maxArea, heightOfBar*width);
		}
		return maxArea;
	}
}
