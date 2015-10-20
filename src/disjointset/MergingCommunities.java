package disjointset;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

import utils.FileUtils;

/**
 * 
 * @author user
 *
 */
public class MergingCommunities {
	
	public static int rank[];
	public static int parent[];
	public static long noOfVerticesInComp[];
	
	public static void main(String[] args) {
		boolean readFromFile = true;
		Scanner  sc = null;
		if(readFromFile) {
			File file = FileUtils.getFile("MergingCommunities.txt", MergingCommunities.class);
			try {
				sc = new Scanner(file);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		} else {
			sc = new Scanner(System.in);
		}
		//Scanner  sc = new Scanner(System.in);
		int num = sc.nextInt();
		
		rank = new int[num + 1];
		parent = new int[num + 1];
		noOfVerticesInComp = new long[num + 1];
		
		Arrays.fill(rank, 0);
		Arrays.fill(noOfVerticesInComp, 1);
		for (int i = 0; i <=num; i++) {
			parent[i] = i;
		}
		int noOfQueries = sc.nextInt();
		String type;
		for (int i = 0; i < noOfQueries; i++) {
			type = sc.next();
			if("M".equals(type)) {
				int comm1 = sc.nextInt();
				int comm2 = sc.nextInt();
				union(comm1, comm2);
			} else if("Q".equals(type)) {
				int comm1 = sc.nextInt();
				int findSet = findSet(comm1);
				System.out.println(noOfVerticesInComp[findSet]);
			}
		}
	}
	
	public static void union(int vertex1, int vertex2) {
		int findSet1 = findSet(vertex1);
		int findSet2 = findSet(vertex2);
		if(findSet1 != findSet2)
			link(findSet1, findSet2);
	}
	
	public static void link(int parent1, int parent2) {
		int rankParent1 = rank[parent1];
		int rankParent2 = rank[parent2];
		if(rankParent1 < rankParent2) {
			parent[parent1] = parent2;
			noOfVerticesInComp[parent2] = noOfVerticesInComp[parent1] + noOfVerticesInComp[parent2];
			noOfVerticesInComp[parent1] = 0;
		} else {
			if(rankParent1 == rankParent2) {
				parent[parent1] = parent2;
				noOfVerticesInComp[parent2] = noOfVerticesInComp[parent1] + noOfVerticesInComp[parent2];
				noOfVerticesInComp[parent1] = 0;
				rank[parent2]++;
			} else {
				//rankParent1 > rankParent2
				parent[parent2] = parent1;
				noOfVerticesInComp[parent1] = noOfVerticesInComp[parent1] + noOfVerticesInComp[parent2];
				noOfVerticesInComp[parent2] = 0;
			}
		}
	}
	
	public static int findSet(int vertexId) {
		if(parent[vertexId] != vertexId) {
			parent[vertexId] = findSet(parent[vertexId]);
		}
		return parent[vertexId];
	}
}
