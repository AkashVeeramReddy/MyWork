package strings;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * https://leetcode.com/problems/word-ladder/
 * 
 * Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation sequence from beginWord to endWord, such that:

Only one letter can be changed at a time
Each intermediate word must exist in the word list
For example,

Given:
beginWord = "hit"
endWord = "cog"
wordList = ["hot","dot","dog","lot","log"]
As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
return its length 5.

Note:
Return 0 if there is no such transformation sequence.
All words have the same length.
All words contain only lowercase alphabetic characters.
 * 
 * TODO: See geeksforgeeks soln for optimised code
 * http://www.geeksforgeeks.org/length-of-shortest-chain-to-reach-a-target-word/
 * Length of shortest chain to reach a target word
Given a dictionary, and two words ‘start’ and ‘target’ (both of same length). Find length of the smallest chain from ‘start’ to ‘target’ if it exists, such that adjacent words in the chain only differ by one character and each word in the chain is a valid word i.e., it exists in the dictionary. It may be assumed that the ‘target’ word exists in dictionary and length of all dictionary words is same.

Example:

Input:  Dictionary = {POON, PLEE, SAME, POIE, PLEA, PLIE, POIN}
             start = TOON
             target = PLEA
Output: 7
Explanation: TOON - POON - POIN - POIE - PLIE - PLEE - PLEA
 * @author user
 *
 */
public class ShortestChainTargetWord {
	public static int ladderLength(String beginWord, String endWord, Set<String> wordList) {
		if(beginWord.equals(endWord))
			return 1;
        wordList.add(beginWord);
        wordList.add(endWord);
        
        List<String> words = new ArrayList<String>(wordList);
        for (int i = 0; i < words.size(); i++) {
			if(words.get(i).equals(beginWord)) {
				srcWordIdx = i;
			}
			if(words.get(i).equals(endWord)) {
				endWordIdx = i;
			}
		}
        int numVertices = words.size();
		VISITED = new boolean[numVertices];
		PRED_VERTEX = new int[numVertices];
		DIST_FROM_SRC = new int[numVertices];

		// Inner list contains the adjacency info for ith vertex which is at ith
		// position in adjList
		List<List<Integer>> adjList = new ArrayList<List<Integer>>();
		for (int i = 0; i < numVertices; i++) {
			adjList.add(new ArrayList<Integer>());
		}
        for (int i = 0; i < words.size(); i++) {
        	for (int j = 0; j < words.size(); j++) {
    			if(i < j) {
    				if ( isAdjacentWord(words.get(i), words.get(j))) {
    					adjList.get(i).add(j);
    					adjList.get(j).add(i);
    				}
    			}
    		}
		}
        return bfs(adjList, srcWordIdx);
    }
	
	public static boolean isAdjacentWord(String str1,String str2) {
		//both are assumed of equal length
		int count = 0;
		for (int i = 0; i < str1.length(); i++) {
			if(str1.charAt(i) != str2.charAt(i)) {
				count++;
			}
		}
		return count == 1;
	}
 
	
	public static boolean[] VISITED;
	//-1 indicates no predecessor, 0 indicates traversal started from that vertex
	public static int[] PRED_VERTEX;
	public static int[] DIST_FROM_SRC;

	public static List<String> words;
	public static int endWordIdx;
	public static int srcWordIdx;

	public static void main(String[] args) {
		String[] words = {"POON", "PLEE", "SAME", "POIE", "PLEA", "PLIE", "POIN"};
		Set<String> dict = new HashSet<String>();
		for (String string : words) {
			dict.add(string);
		}
		System.out.println(ladderLength("TOON", "PLEA", dict));
	}
	
	/**
	 * 
	 * @param adjList
	 * @param srcIdx
	 * @return
	 */
	public static int bfs(List<List<Integer>> adjList, int srcIdx) {
		LinkedList<Integer> queue = new LinkedList<Integer>();
		queue.add(srcIdx);
		VISITED[srcIdx] = true;
		DIST_FROM_SRC[srcIdx] = 1;
		PRED_VERTEX[srcIdx] = 0;
		
		int dequedVertexIdx;
		while(!queue.isEmpty()) {
			dequedVertexIdx = queue.poll();
			List<Integer> adj = adjList.get(dequedVertexIdx);
			for (Integer adjVertIdx : adj) {
				if(!VISITED[adjVertIdx]) {
					VISITED[adjVertIdx] = true;
					queue.add(adjVertIdx);
					DIST_FROM_SRC[adjVertIdx] = DIST_FROM_SRC[dequedVertexIdx] + 1;
					PRED_VERTEX[adjVertIdx] = dequedVertexIdx;
					if(endWordIdx == adjVertIdx) {
						return DIST_FROM_SRC[adjVertIdx];
					}
				}
			}
		}
		return -1;
	}


}
