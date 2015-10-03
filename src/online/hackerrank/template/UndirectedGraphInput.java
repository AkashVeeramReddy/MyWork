package online.hackerrank.template;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import utils.FileUtils;
/**
 * n - number of vertices from 1..n
 * then m edges
 * then in each line for the next m lines edges are give
 * 
 * 3 vertices. 2 edges ie 1-2 and 1-3
 * 3
 * 2
 * 1 2
 * 1 3
 * @author user
 *
 */
public class UndirectedGraphInput {

	
	public static boolean[] VISITED;
	public static int[] DISTANCE_FRM_SRC;
	
	public static void main(String[] args) {
		boolean readFromFile = true;
		Scanner  sc = null;
		if(readFromFile) {
			File file = FileUtils.getFile("UndirectedGraphInput.txt", UndirectedGraphInput.class);
			try {
				sc = new Scanner(file);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		} else {
			sc = new Scanner(System.in);
		}
		//Scanner  sc = new Scanner(System.in);
		int numVertices;
		numVertices = sc.nextInt();
		VISITED = new boolean[numVertices];
		DISTANCE_FRM_SRC = new int[numVertices];
		
		
		//Inner list contains the adjacency info for ith vertex which is at ith position in adjList
		List<List<Integer>> adjList = new ArrayList<List<Integer>>();
		for (int i = 0; i < numVertices; i++) {
			adjList.add(new ArrayList<Integer>());
		}
		
		
		//For edges
		int noOfEdges = sc.nextInt();
		int srcVertex,destVertex;
		for (int i = 0; i < noOfEdges; i++) {
			srcVertex = sc.nextInt();
			destVertex = sc.nextInt();
			
			adjList.get(srcVertex-1).add(destVertex);
			adjList.get(destVertex-1).add(srcVertex);
		}
		
	}

}
