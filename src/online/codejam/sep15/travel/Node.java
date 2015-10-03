package online.codejam.sep15.travel;


public class Node implements Comparable<Node>{
	public long timeFrm =  Long.MAX_VALUE;
	public Node[] adjListNodes;
	public int[][] times;
	public int id;
	public boolean isInQueue = true;
	
	public Node(int id, int noOfNodes) {
		this.id = id;
		adjListNodes = new Node[noOfNodes];
		times = new int[noOfNodes][24];
	}

	@Override
	public int compareTo(Node o) {
		return Long.compare(timeFrm, o.timeFrm);
	}
}
