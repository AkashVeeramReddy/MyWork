package stack;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import utils.FileUtils;

public class WaterTrap {
	public static void main(String[] args) {
		/*File file = FileUtils.getFile("testCase.txt", WaterTrap.class);
		String fileContents = null;
		try {
			fileContents = FileUtils.getFileContents(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String trim = fileContents.trim();
		String substring = trim.substring(1, trim.length() - 1);
		String[] split = substring.split(",");
		List<Integer> integers = new ArrayList<Integer>();
		for (String string : split) {
			trim = string.trim();
			try {
				int parseInt = Integer.parseInt(trim);
				integers.add(parseInt);
			} catch (Exception e) {
			}

		}*/
		//Integer[] array = integers.toArray(new Integer[] {});
		Integer[] array = {0,1,0,2,1,0,1,3,2,1,2,1};
		int trap = trap(array);
		System.out.println(trap);
	}
	
	public static int trap(Integer[] height) {
        //index of integers
        LinkedList<Integer> stack = new LinkedList<Integer>();
        int sum = 0;
        for(int i=0;i<height.length;i++) {
            int ele = height[i];
            //int minEle = Integer.MAX_VALUE;
            //int maxEle = Integer.MIN_VALUE;
            Integer firstPoppedEle = null;
            Integer lastPoppedEle = null;
            //System.out
            while(!stack.isEmpty() && height[stack.getLast()]<ele) {
                lastPoppedEle = stack.removeLast();
                System.out.println("lastPoppedIdx "+lastPoppedEle);
                int heightArea = 0;
                if(!stack.isEmpty()) {
                    heightArea = Math.min(height[stack.getLast()],height[i]) - height[lastPoppedEle];
                    System.out.println("heightArea "+heightArea);
                }
                sum = sum + (stack.isEmpty()?0:(i-stack.getLast()-1)*heightArea);
            }
            stack.addLast(i);
        }
        return sum;
    }
}
