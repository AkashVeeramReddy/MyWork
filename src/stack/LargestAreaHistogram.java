package stack;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import utils.FileUtils;
/**
 * asked in Inmobi placement exam
 * http://www.geeksforgeeks.org/largest-rectangle-under-histogram/
 * 
 * Find the largest rectangular area possible in a given histogram where the largest rectangle can be made of a number of contiguous bars. For simplicity, assume that all bars have same width and the width is 1 unit.
 * @author user
 *
 */
public class LargestAreaHistogram {
	public static void main(String[] args) {
		//int []arr = {6,2,5,4,5,1,6};
		int []arr = {0,-1,-1,0};
		int largestArea = getLargestArea(arr);
		System.out.println(largestArea);
	}
	static int getLargestArea(int[] arr) {
        //index of integers
        LinkedList<Integer> stack = new LinkedList<Integer>();
        int len = arr.length;
        int maxArea = 0;
        int area;
        int heightArea = 0;
        for(int i=0;i<arr.length;i++) {
            int ele = arr[i];
            //int minEle = Integer.MAX_VALUE;
            //int maxEle = Integer.MIN_VALUE;
            Integer firstPoppedEle = null;
            Integer lastPoppedEle = null;
            
            
            //System.out
            while(!stack.isEmpty() && ele<arr[stack.getLast()]) {
                lastPoppedEle = stack.removeLast();
                //System.out.println("lastPoppedIdx "+lastPoppedEle);
                
                heightArea = arr[lastPoppedEle];
                if(!stack.isEmpty()) {
                    //System.out.println("heightArea "+heightArea);
                }
                area = (stack.isEmpty()?i:(i-stack.getLast()-1))*heightArea;
                maxArea = Math.max(area,maxArea);
                /*
                if(firstPoppedEle == null)
                    firstPoppedEle = lastPoppedEle;
            }
            /*
            if(firstPoppedEle != null && lastPoppedEle != null) {
                int heightArea = height[lastPoppedEle] - height[firstPoppedEle];
                int widthArea = i - lastPoppedEle -1;
                sum = sum + heightArea*widthArea;
            }
            */
            
            }
            stack.addLast(i);
        }
        Integer lastPoppedEle = null;
        while(!stack.isEmpty()) {
            lastPoppedEle = stack.removeLast();
            heightArea = arr[lastPoppedEle];
            area = (stack.isEmpty()?len:(len-stack.getLast()-1))*heightArea;
            maxArea = Math.max(area,maxArea);
        }
        //System.out.println("Max area "+maxArea);
        return maxArea;

    }
}
