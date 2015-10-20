package graphs.shortestpath;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import utils.FileUtils;

public class ShortestDistanceDag {
	//For DFS
	public static boolean[] VISITED;
	
	//For Distance Calculation.
	//null value indicates not reachable
	public static Long[] DIST_FRM_SRC;
	//-1 indicates no predecessor, 0 indicates traversal started from that vertex
	public static int[] PRED_VERTEX;
	
	//For both DFS and Distance Calculation
	public static List<List<EdgeInfo>> EDGE_INFO_LIST;
	public static LinkedList<Integer> FINISHING_TIMES_DEC = new LinkedList<Integer>();
	public static void main(String[] args) {
		int srcVertex = readInputAndReturnSrcVertex();
		dfs();
		calculateDistanceFrmSrc(srcVertex);
		printDistanceAndPath(srcVertex);
		
	}
	private static void printDistanceAndPath(int srcVertex) {
		System.out.println("Source Vertex: "+srcVertex);
		StringBuilder builder = new StringBuilder();
		int vertexId ;
		for (int i = 0; i < EDGE_INFO_LIST.size(); i++) {
			vertexId = i+1;
			builder.append("Dest Vertex:"+vertexId);
			builder.append(",Dist Frm Src ");
			builder.append(DIST_FRM_SRC[i]);
			builder.append(",Path ");
			builder.append(GraphUtils.getPath(srcVertex, vertexId, PRED_VERTEX));
			builder.append("\n");
		}
		System.out.println(builder.toString());
	}
	public static void dfs() {
		for (int i = 0; i < EDGE_INFO_LIST.size(); i++) {
			if(!VISITED[i]) {
				VISITED[i] = true;
				//FINISHING_TIMES_DEC.addFirst(i+1);
				dfsVisit(i+1);
			}
		}
	}

	private static void dfsVisit(int parentId) {
		List<EdgeInfo> list = EDGE_INFO_LIST.get(parentId-1);
		for (EdgeInfo edge : list) {
			int childId = edge.destVertexId;
			if(!VISITED[childId-1]) {
				VISITED[childId-1] = true;
				dfsVisit(childId);
			}
		}
		FINISHING_TIMES_DEC.addFirst(parentId);
	}
	private static void calculateDistanceFrmSrc(int srcId) {
		DIST_FRM_SRC[srcId-1] = (long) 0;
		PRED_VERTEX[srcId-1] = 0;
		List<EdgeInfo> list;
		int destVertex,edgeCost;
		Long distFrmSrc,possChildCost;
		for (Integer vertexId : FINISHING_TIMES_DEC) {
			list = EDGE_INFO_LIST.get(vertexId-1);
			distFrmSrc = DIST_FRM_SRC[vertexId-1];
			if(distFrmSrc != null) {
				for (EdgeInfo edgeInfo : list) {
					destVertex = edgeInfo.destVertexId;
					edgeCost = edgeInfo.edgeCost;
					possChildCost = distFrmSrc + edgeCost;
					if(DIST_FRM_SRC[destVertex-1] == null||possChildCost < DIST_FRM_SRC[destVertex-1]) {
						DIST_FRM_SRC[destVertex-1] = possChildCost;
						PRED_VERTEX[destVertex-1] = vertexId;
					}
				}
			}
		}
		
	}
	private static int readInputAndReturnSrcVertex() {
		boolean readFromFile = true;
		Scanner sc = null;
		if (readFromFile) {
			File file = FileUtils.getFile("ShortestDistanceDag.txt",
					ShortestDistanceDag.class);
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
		DIST_FRM_SRC = new Long[numVertices];
		// Inner list contains the adjacency info for ith vertex which is at ith
		// position in adjList
		EDGE_INFO_LIST = new ArrayList<List<EdgeInfo>>();
		for (int i = 0; i < numVertices; i++) {
			EDGE_INFO_LIST.add(new ArrayList<EdgeInfo>());
		}

		// For edges
		int noOfEdges = sc.nextInt();
		int srcVertex, destVertex,edgeCost;
		for (int i = 0; i < noOfEdges; i++) {
			srcVertex = sc.nextInt();
			destVertex = sc.nextInt();
			edgeCost = sc.nextInt();
			
			EdgeInfo edgeInfo = new EdgeInfo(edgeCost,destVertex);
			EDGE_INFO_LIST.get(srcVertex - 1).add(edgeInfo);
		}
		return 1;
	}
	
	private static class EdgeInfo {
		public int edgeCost;
		public int destVertexId;
		
		public EdgeInfo(int edgeCost, int destVertexId) {
			this.edgeCost = edgeCost;
			this.destVertexId = destVertexId;
		}

		@Override
		public String toString() {
			return "[cost=" + edgeCost + ", destId="
					+ destVertexId + "]";
		}
	}
}
