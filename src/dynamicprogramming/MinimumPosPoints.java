package dynamicprogramming;
/**
 * http://www.geeksforgeeks.org/minimum-positive-points-to-reach-destination/
 * 
 * Minimum Initial Points to Reach Destination
Given a grid with each cell consisting of positive, negative or no points i.e, zero points. We can move across a cell only if we have positive points ( > 0 ). Whenever we pass through a cell, points in that cell are added to our overall points. We need to find minimum initial points to reach cell (m-1, n-1) from (0, 0).

Constraints :

From a cell (i, j) we can move to (i+1, j) or (i, j+1).
We cannot move from (i, j) if your overall points at (i, j) is <= 0.
We have to reach at (n-1, m-1) with minimum positive points i.e., > 0.
Example:

Input: points[m][n] = { {-2, -3,   3}, 
                        {-5, -10,  1}, 
                        {10,  30, -5} 
                      };
Output: 7
Explanation: 
7 is the minimum value to reach destination with 
positive throughout the path. Below is the path.

(0,0) -> (0,1) -> (0,2) -> (1, 2) -> (2, 2)

We start from (0, 0) with 7, we reach(0, 1) 
with 5, (0, 2) with 2, (1, 2) with 5, (2, 2)
with and finally we have 1 point (we needed 
greater than 0 points at the end). 
 * @author user
 *
 */
public class MinimumPosPoints {
	public static void main(String[] args) {
		Integer points[][] = { {-2,-3,3},
                 {-5,-10,1},
                 {10,30,-5}
               };
		Integer minimumPosPoints = getMinimumPosPoints(points);
		System.out.println(minimumPosPoints);
	}
	
	public static Integer getMinimumPosPoints(Integer [][] points) {
		//m - rows n-columns
		Integer m = points.length;
		Integer n = points[0].length;
		Integer [][] dp = new Integer[m][n];
		if(points[m-1][n-1] > 0) {
			dp[m-1][n-1] = 1;
		} else {
			dp[m-1][n-1] = -points[m-1][n-1] + 1;
		}
		for (int i = m-2; i >= 0; i--)
	         dp[i][n-1] = Math.max(dp[i+1][n-1] - points[i][n-1], 1);
	    for (int j = n-2; j >= 0; j--)
	         dp[m-1][j] = Math.max(dp[m-1][j+1] - points[m-1][j], 1);
	 // fill the table in bottom-up fashion
	    for (int i=m-2; i>=0; i--)
	    {
	        for (int j=n-2; j>=0; j--)
	        {
	            int min_points_on_exit = Math.min(dp[i+1][j], dp[i][j+1]);
	            dp[i][j] = Math.max(min_points_on_exit - points[i][j], 1);
	        }
	     }
		return dp[0][0];
	}
	
}
