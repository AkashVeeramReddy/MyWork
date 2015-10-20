package graphs.shortestpath;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

import utils.FileUtils;

public class DijkstraDirectedGraph {
	//0 indicates no predecessor
	public static int[] PRED_VERTEX;
	//null value indicates not reachable
	public static Long[] DIST_FRM_SRC;
	public static  List<List<EdgeInfo>> EDGE_INFO_LIST;
	
	public static void main(String[] args) {
		int srcId = readInputAndReturnSrc();
		dijkstra(EDGE_INFO_LIST, srcId);
		System.out.println(Arrays.toString(DIST_FRM_SRC));
	}

	private static int readInputAndReturnSrc() {
		boolean readFromFile = true;
		Scanner sc = null;
		if (readFromFile) {
			File file = FileUtils.getFile("DijkstraDirectedGraph.txt",
					DijkstraDirectedGraph.class);
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
	
	public static void dijkstra(List<List<EdgeInfo>> edgeInfoList, int srcId) {
		DIST_FRM_SRC[srcId-1] = (long) 0;
		PriorityQueue<VertexHeapInfo> queue = new PriorityQueue<VertexHeapInfo>();
		VertexHeapInfo srcInfo = new VertexHeapInfo(srcId,0);
		queue.add(srcInfo);
		//for getting element from heap and inserting
		VertexHeapInfo vertexInfo;
		boolean []isDistFinalized = new boolean[edgeInfoList.size()];
		Long distFrmSrc,possChildDistFrmSrc,distChildVertex;
		while(!queue.isEmpty()) {
			VertexHeapInfo deqVert = queue.poll();
			int vertexId = deqVert.vertexId;
			isDistFinalized[vertexId-1] = true;
			distFrmSrc = DIST_FRM_SRC[vertexId-1];
			if(distFrmSrc != null) {
				List<EdgeInfo> edges = edgeInfoList.get(vertexId-1);
				for (EdgeInfo edgeInfo : edges) {
					int destVertexId = edgeInfo.destVertexId;
					if(!isDistFinalized[destVertexId-1]) {
						possChildDistFrmSrc = distFrmSrc + edgeInfo.edgeCost;
						distChildVertex = DIST_FRM_SRC[destVertexId-1];
						if(distChildVertex == null || possChildDistFrmSrc < distChildVertex) {
							DIST_FRM_SRC[destVertexId-1] = possChildDistFrmSrc;
							PRED_VERTEX[destVertexId-1] = vertexId;
							//decrease key
							vertexInfo = new VertexHeapInfo(destVertexId, possChildDistFrmSrc);
							//remove will old vertex info whose id is same
							queue.remove(vertexInfo);
							queue.add(vertexInfo);
						}
					}
				}
			}
		}
		
	}
	
	private static class EdgeInfo {
		public int edgeCost;
		public int destVertexId;
		
		public EdgeInfo(int edgeCost, int destVertexId) {
			this.edgeCost = edgeCost;
			this.destVertexId = destVertexId;
		}
	}
	
	private static class VertexHeapInfo implements Comparable<VertexHeapInfo>{
		public long distFrmSrc = Long.MAX_VALUE;
		public int vertexId;
		
		public VertexHeapInfo(int vertexId, long distFrmSrc) {
			this.distFrmSrc = distFrmSrc;
			this.vertexId = vertexId;
		}
		
		@Override
		public boolean equals(Object obj) {
			if(obj instanceof VertexHeapInfo) {
				return vertexId == ((VertexHeapInfo)obj).vertexId;
			}
			return false;
		}

		@Override
		public int compareTo(VertexHeapInfo o) {
			return Long.compare(distFrmSrc, o.distFrmSrc);
		}
	}
}
