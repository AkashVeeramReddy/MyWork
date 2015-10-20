package graphs.shortestpath;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import utils.FileUtils;

public class AllPairsShortestPath {
	//0 indicates no predecessor
	public static int[][] PRED_MATRIX;
	//null value indicates not reachable
	public static Long[][] DIST_MATRIX;
	public static Integer[][] EDGECOST_MATRIX;
	public static int NUM_VERTICES;
	
	public static void main(String[] args) {
		readData();
		performAllPairsShortestPaths();
	}
	
	public static void performAllPairsShortestPaths() {
		Long[][] prevItrDistMatrix = new Long[NUM_VERTICES][NUM_VERTICES];
		//ArrayUtilities.copyArray(EDGECOST_MATRIX, prevItrDistMatrix);
		Long[][] currentDistMatrix = new Long[NUM_VERTICES][NUM_VERTICES];
		Long[][] tempDist;
		
		int[][] prevItrPred = new int[NUM_VERTICES][NUM_VERTICES];
		int[][] curItrPred = new int[NUM_VERTICES][NUM_VERTICES];
		int[][] tempPred;
		
		Integer edgeCost;
		for (int i = 0; i < NUM_VERTICES; i++) {
			for (int j = 0; j < NUM_VERTICES; j++) {
				edgeCost = EDGECOST_MATRIX[i][j];
				if(edgeCost != null) {
					prevItrDistMatrix[i][j] = (long)edgeCost;
					prevItrPred[i][j] = i+1;
				}
			}
		}
		
		long distInPrevIter,distInCurrentItr;
		int interMedVertexIdx;
		for (int interMedVertexIdItr = 1; interMedVertexIdItr <=NUM_VERTICES; interMedVertexIdItr++) {
			
			tempDist = currentDistMatrix;
			currentDistMatrix = new Long[NUM_VERTICES][NUM_VERTICES];
			prevItrDistMatrix = tempDist;
			
			tempPred = curItrPred;
			curItrPred = new int[NUM_VERTICES][NUM_VERTICES];
			prevItrPred = tempPred;
			
			interMedVertexIdx = interMedVertexIdItr-1;
			for (int i = 0; i < NUM_VERTICES; i++) {
				for (int j = 0; j < NUM_VERTICES; j++) {
					distInPrevIter = prevItrDistMatrix[i][j];
					distInCurrentItr = prevItrDistMatrix[i][interMedVertexIdx] 
							+  prevItrDistMatrix[interMedVertexIdItr-1][j];
					if(distInCurrentItr < distInPrevIter) {
						currentDistMatrix[i][j] = distInCurrentItr;	
						curItrPred[i][j] = prevItrPred[interMedVertexIdItr-1][j];
					} else {
						currentDistMatrix[i][j] = distInPrevIter;
						curItrPred[i][j] = prevItrPred[i][j];
					}
				}
			}	
		}
		DIST_MATRIX = currentDistMatrix;
		PRED_MATRIX = curItrPred;
	}

	private static void readData() {
		boolean readFromFile = true;
		Scanner sc = null;
		if (readFromFile) {
			File file = FileUtils.getFile("AllPairsShortestPath.txt",
					AllPairsShortestPath.class);
			try {
				sc = new Scanner(file);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		} else {
			sc = new Scanner(System.in);
		}
		// Scanner sc = new Scanner(System.in);
		NUM_VERTICES = sc.nextInt();

		// Inner list contains the adjacency info for ith vertex which is at ith
		// position in adjList
		EDGECOST_MATRIX = new Integer[NUM_VERTICES][NUM_VERTICES];
		// For edges
		int noOfEdges = sc.nextInt();
		int srcVertex, destVertex,edgeCost;
		for (int i = 0; i < noOfEdges; i++) {
			srcVertex = sc.nextInt();
			destVertex = sc.nextInt();
			edgeCost = sc.nextInt();
			
			EDGECOST_MATRIX[srcVertex-1][destVertex-1] = edgeCost;
		}
	}
}
