package graphs.traversal;

import graphs.shortestpath.GraphUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import utils.FileUtils;

public class DFSUndirectedGraph {
	public static boolean[] VISITED;
	//-1 indicates no predecessor, 0 indicates traversal started from that vertex
	public static int[] PRED_VERTEX;

	public static void main(String[] args) {
		boolean readFromFile = true;
		Scanner sc = null;
		if (readFromFile) {
			File file = FileUtils.getFile("DFSUndirectedGraph.txt",
					DFSUndirectedGraph.class);
			try {
				sc = new Scanner(file);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		} else {
			sc = new Scanner(System.in);
		}
		// Scanner sc = new Scanner(System.in);
		int numVertices;
		numVertices = sc.nextInt();
		VISITED = new boolean[numVertices];
		PRED_VERTEX = new int[numVertices];

		// Inner list contains the adjacency info for ith vertex which is at ith
		// position in adjList
		List<List<Integer>> adjList = new ArrayList<List<Integer>>();
		for (int i = 0; i < numVertices; i++) {
			adjList.add(new ArrayList<Integer>());
		}

		// For edges
		int noOfEdges = sc.nextInt();
		int srcVertex, destVertex;
		for (int i = 0; i < noOfEdges; i++) {
			srcVertex = sc.nextInt();
			destVertex = sc.nextInt();

			adjList.get(srcVertex - 1).add(destVertex);
			adjList.get(destVertex - 1).add(srcVertex);
		}
		dfs(adjList);
		System.out.println("Pred Info" + Arrays.toString(PRED_VERTEX));
		System.out.println("Paths");
		for (int i = 0; i < adjList.size(); i++) {
			System.out.println("DFS path of "+ (i+1)+" :"+GraphUtils.getPath(0, i+1, PRED_VERTEX));
		}
	}
	
	public static void dfs(List<List<Integer>> adjList) {
		for (int i = 0; i < adjList.size(); i++) {
			if(!VISITED[i]) {
				VISITED[i] = true;
				PRED_VERTEX[i] = 0;
				dfsVisit(adjList,i+1);
			}
		}
	}
	

	private static void dfsVisit(List<List<Integer>> adjList, int parentId) {
		List<Integer> list = adjList.get(parentId-1);
		for (Integer childId : list) {
			if(!VISITED[childId-1]) {
				VISITED[childId-1] = true;
				PRED_VERTEX[childId-1] = parentId;
				dfsVisit(adjList,childId);
			}
		}
	}
}
