package dynamicprogramming;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.

For example, given the following triangle
[
     [2],
    [3,4],
   [6,5,7],
  [4,1,8,3]
]
The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).

Note:
Bonus point if you are able to do this using only O(n) extra space, where n is the total number of rows in the triangle.
 * 
 * https://leetcode.com/problems/triangle/
 * @author user
 *
 */
public class TriangleMinSumTopToBottom {
	public static int minimumTotal(List<List<Integer>> triangle) {
        int noOfRows = triangle.size();
        if(noOfRows == 0) {
            return 0;
        }
        List<Integer> minSumPrevRow = new ArrayList<Integer>(triangle.get(0));
        List<Integer> minSumCurRow;
        List<Integer> curRow;
        int minSumItr;
        for(int i=1; i< noOfRows ; i++) {
            curRow = triangle.get(i);
            minSumCurRow = new ArrayList<Integer>();
            for(int j=0;j<=i;j++) {
                if(j==0) {
                    minSumItr = minSumPrevRow.get(0) + curRow.get(0);
                } else if(j == i) {
                    minSumItr = minSumPrevRow.get(j-1) + curRow.get(j);
                } else {
                    minSumItr = curRow.get(j) + Math.min(minSumPrevRow.get(j-1),minSumPrevRow.get(j));
                }
                minSumCurRow.add(minSumItr);
            }
            minSumPrevRow = minSumCurRow;
        }
        return Collections.min(minSumPrevRow);
    }
}
