package online.codejam.sep15.travel;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Graph {
	public Node[] nodes;
	public int size;
	
	public Graph(int size) {
		this.size = size;
		nodes = new Node[size];
		for (int i = 0; i < size; i++) {
			nodes[i] = new Node(i+1,size);
		}
	}
	
	public void addRoad(int vertex1, int vertex2, int []hours) {
		Node node1 = nodes[vertex1-1];
		Node node2 = nodes[vertex2-1];
		
		//add in 1
		node1.adjListNodes[vertex2 -1] = node2;
		node1.times[vertex2-1] = hours;
		//add in 2
		node2.adjListNodes[vertex1 -1] = node1;
		node2.times[vertex1-1] = hours;
	}
	
	public void clearValues() {
		for (int i = 0; i < size; i++) {
			nodes[i].timeFrm = Long.MAX_VALUE;
			nodes[i].isInQueue = true;
		}
		nodes[0].timeFrm = 0;
	}
	
	public long findDistance(int dest, long startTime) {
		long dist = 0;
		long timeTaken = 0;
		PriorityQueue<Node> queue = new PriorityQueue<Node>(Arrays.asList(nodes));
		while(!queue.isEmpty()) {
			Node remove = queue.remove();
			timeTaken = remove.timeFrm;
			remove.isInQueue = false;
			if(remove.id == dest) {
				return remove.timeFrm;
			} else {
				if(timeTaken == Long.MAX_VALUE) {
					return timeTaken;
				}
				for (int i = 0; i < size; i++) {
					Node itr = nodes[i];
					if(itr != null && itr.isInQueue) {
						boolean isRoadPresnt = (remove.adjListNodes[i] != null);
						if(!isRoadPresnt) {
							continue;
						}
						long curTime = startTime + timeTaken;
						long newTime = timeTaken + remove.times[itr.id-1][(int) (curTime%24)];
						if(newTime < itr.timeFrm) {
							queue.remove(itr);
							itr.timeFrm = Math.min(itr.timeFrm,newTime);;  
							queue.add(itr);
						}
					}
				}
			}
		}
		return dist;
	}
}
