package graphs.traversal;

import graphs.shortestpath.GraphUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import utils.FileUtils;

public class BFSUnDirectedGraph {
	
	public static boolean[] VISITED;
	//-1 indicates no predecessor, 0 indicates traversal started from that vertex
	public static int[] PRED_VERTEX;
	public static int[] DIST_FROM_SRC;

	public static void main(String[] args) {
		boolean readFromFile = true;
		Scanner sc = null;
		if (readFromFile) {
			File file = FileUtils.getFile("BFSUnDirectedGraph.txt",
					BFSUnDirectedGraph.class);
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
		DIST_FROM_SRC = new int[numVertices];

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
		bfs(adjList, 1);
		System.out.println("Pred Info" + Arrays.toString(PRED_VERTEX));
		System.out.println("Dist Info" + Arrays.toString(DIST_FROM_SRC));
		System.out.println("Paths from Source Vertex Id:"+1);
		for (int i = 0; i < adjList.size(); i++) {
			System.out.print(i+1);
			System.out.print(":");
			LinkedList<Integer> path = GraphUtils.getPath(1, (i+1), PRED_VERTEX);
			System.out.println(path);
		}
	}
	
	public static void bfs(List<List<Integer>> adjList, int srcId) {
		LinkedList<Integer> queue = new LinkedList<Integer>();
		queue.add(srcId);
		VISITED[srcId-1] = true;
		DIST_FROM_SRC[srcId-1] = 0;
		PRED_VERTEX[srcId - 1] = 0;
		
		int adjVertIdx,dequedVertexIdx;
		while(!queue.isEmpty()) {
			Integer dequedVertexId = queue.poll();
			dequedVertexIdx = dequedVertexId-1;
			List<Integer> adj = adjList.get(dequedVertexIdx);
			for (Integer adjVertexId : adj) {
				adjVertIdx = adjVertexId-1;
				if(!VISITED[adjVertIdx]) {
					VISITED[adjVertIdx] = true;
					queue.add(adjVertexId);
					DIST_FROM_SRC[adjVertIdx] = DIST_FROM_SRC[dequedVertexIdx] + 1;
					PRED_VERTEX[adjVertIdx] = dequedVertexId;
				}
			}
		}
	}

}
