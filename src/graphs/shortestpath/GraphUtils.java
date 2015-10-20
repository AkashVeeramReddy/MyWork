package graphs.shortestpath;

import java.util.LinkedList;


public class GraphUtils {
	/**
	 * @param srcVertexId 0 means src id unknown(eg: in DFS)
	 * @param destVertexId
	 * @param predVertex - -1 indicates no predecessor, 0 indicates traversal started from that vertex
	 * @return
	 */
	public static LinkedList<Integer> getPath(int srcVertexId, int destVertexId, int[] predVertex) {
		LinkedList<Integer> path = new LinkedList<Integer>(); 
		int itrVertex = destVertexId;
		while(predVertex[itrVertex-1] != -1 && predVertex[itrVertex-1] != 0 ) {
			path.addFirst(itrVertex);
			itrVertex = predVertex[itrVertex-1];
		}
		if(predVertex[itrVertex-1] == 0) {
			path.addFirst(itrVertex);
		}
		return path;
	}
	
}
