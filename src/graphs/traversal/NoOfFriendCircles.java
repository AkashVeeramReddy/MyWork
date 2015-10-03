package graphs.traversal;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import utils.FileUtils;

/**
 * @see NoOfFriendCircles.pdf,NoOfFriendCircles.txt
 * @author user
 *
 */
public class NoOfFriendCircles {
	public static void main(String[] args) {
		File file = FileUtils.getFile("NoOfFriendCircles2.txt", NoOfFriendCircles.class);
		try {
			Scanner scanner = new Scanner(file);
			int numTC = scanner.nextInt();
			boolean []visited = new boolean[numTC];
			boolean [][]friends = new boolean[numTC][numTC];
			int friendCircles = 0;
			for (int i = 0; i < friends.length; i++) {
				String next = scanner.next();
				char[] arr = next.toCharArray();
				for (int j = 0; j < friends.length; j++) {
					if(arr[j] == 'Y') {
						friends[i][j] = true;
					}
				}
			}
			long cost = 0;
			for (int i = 0; i < visited.length; i++) {
				if(!visited[i]) {
					//friendCircles++;
					long no = dfsVisit(friends,visited,i);
					Double root = Math.pow(no, 0.5);
					Double intValue = (double) root.intValue();
					if(root == intValue) {
						cost = (long) (cost + intValue);
					} else {
						cost = (long) (cost + intValue + 1);
					}
				}
			}
			System.out.println(friendCircles);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}

	public static long dfsVisit(boolean [][] friends, boolean[] visited, int idx) {
		visited[idx] = true;
		boolean[] adjList = friends[idx];
		long sum = 1;
		for (int i = 0; i < adjList.length; i++) {
			if(i != idx && adjList[i] && !visited[i]) {
				sum = sum + dfsVisit(friends, visited, i);
			}
		}
		return sum;
	}
}
