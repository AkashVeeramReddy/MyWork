package graphs.traversal;

import java.util.ArrayList;
import java.util.List;

/**
 * There are a total of n courses you have to take, labeled from 0 to n - 1.
 * 
 * Some courses may have prerequisites, for example to take course 0 you have to
 * first take course 1, which is expressed as a pair: [0,1]
 * 
 * Given the total number of courses and a list of prerequisite pairs, is it
 * possible for you to finish all courses?
 * 
 * For example:
 * 
 * 2, [[1,0]] There are a total of 2 courses to take. To take course 1 you
 * should have finished course 0. So it is possible.
 * 
 * 2, [[1,0],[0,1]] There are a total of 2 courses to take. To take course 1 you
 * should have finished course 0, and to take course 0 you should also have
 * finished course 1. So it is impossible.
 * 
 * Note: The input prerequisites is a graph represented by a list of edges, not
 * adjacency matrices.
 * 
 * https://leetcode.com/problems/course-schedule/
 * 
 * @author user
 * 
 */
public class CourseSchedule {
	public static boolean[] VISITED;
	public static boolean[] FINISHED;
	
	public static boolean canFinish(int numCourses, int[][] prerequisites) {
		VISITED = new boolean[numCourses];
		FINISHED = new boolean[numCourses];
		List<List<Integer>> adjList = new ArrayList<List<Integer>>();
		for (int i = 0; i < numCourses; i++) {
			adjList.add(new ArrayList<Integer>());
		}
		for (int[] edge : prerequisites) {
			adjList.get(edge[1]).add(edge[0]);
		}
		return dfs(adjList);
		
	}
	
	public static boolean dfs(List<List<Integer>> adjList) {
		for (int i = 0; i < adjList.size(); i++) {
			if(!VISITED[i]) {
				VISITED[i] = true;
				boolean dfsVisit = dfsVisit(adjList,i);
				if(dfsVisit == false)
					return false;
			}
		}
		return true;
	}
	

	private static boolean dfsVisit(List<List<Integer>> adjList, int parentId) {
		List<Integer> list = adjList.get(parentId);
		for (Integer childId : list) {
			if(!VISITED[childId]) {
				VISITED[childId] = true;
				boolean dfsVisit = dfsVisit(adjList,childId);
				if(dfsVisit == false)
					return false;
			} else {
				if(!FINISHED[childId]) {
					//cycle. So cant finish
					return false;
				}
			}
		}
		FINISHED[parentId] = true;
		return true;
	}
	
	public static void main(String[] args) {
		int numCourse = 2;
		int[][] preReq = {{1,0},{0,1}};
		boolean canFinish = canFinish(numCourse, preReq);
		System.out.println(canFinish);
	}
}
