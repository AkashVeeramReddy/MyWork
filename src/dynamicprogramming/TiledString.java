package dynamicprogramming;

import java.util.HashSet;
import java.util.Set;

/**
 * Come up with a solution for the following problem. 

Input Word: "BedBathandBeyond"

Assumption: You have already dictionary to look up the word.

public boolean isTiled(String input){// For e.g. for the mentioned input 
				      // "Bed", "Bath", "And" and "Beyond"
				      // "Bed","Bat","Hand" and "Beyond"

}

 *
 */
public class TiledString {
	
	
	public static void isTiledString(String input,Set<String> dictionary) {
		int length = input.length();
		Info [][] infoArray = new Info[length][length] ;
		for(int i = 0;i < length;i++) {
			Info info = new Info();
			info.isTiled = false;
			info.isWord = dictionary.contains(input.charAt(i)+"");
			infoArray[i][i] = info;
		}
		for(int lengthItr=2;lengthItr<=length;lengthItr++) {
			for(int startIndexItr=0;startIndexItr<=(length-lengthItr);startIndexItr++) {
				Info info = new Info();
				int endIndexItr = startIndexItr + lengthItr - 1;
				infoArray[startIndexItr][endIndexItr] = info;
				String substring = input.substring(startIndexItr, endIndexItr+1);
				if(dictionary.contains(substring)) {
					info.isWord = true;
				}
				boolean isSubArrayCheckReqd = false;
				if(!info.isWord || lengthItr==length)
					isSubArrayCheckReqd = true;
				if(isSubArrayCheckReqd) {
					for(int subArrayItr = startIndexItr;subArrayItr<endIndexItr;subArrayItr++) {
						Info leftSubArray = infoArray[startIndexItr][subArrayItr];
						Info rightSubArray = infoArray[subArrayItr+1][endIndexItr];
						if(leftSubArray.isTiledOrWord() && rightSubArray.isTiledOrWord()) {
							info.isTiled = true;
							info.split = subArrayItr;
							break;
						}
					}
				}
			}
		}
		Info info = infoArray[0][length-1];
		boolean isTiled = info.isTiled;
		if(isTiled) {
			System.out.println("Word '" + input + "' is tiled with words");
			printTiles(infoArray,0,length-1,input);
		} else {
			System.out.println("Word '" + input + "'is not tiled");
		}
	}
	
	private static void printTiles(Info[][] infoArray, int start, int end,String string) {
		if(start <= end) {
			Info info = infoArray[start][end];
			if(info.isWord) {
				System.out.println(string.substring(start,end+1));
			} else if(info.isTiled){
				int split = info.split;
				printTiles(infoArray, start, split, string);
				printTiles(infoArray, split+1, end, string);
			}
		}
	}

	private static class Info {
		private boolean isWord;
		private boolean isTiled;
		/**
		 * if string a[i...j] is split at k then a[i..k] and a[k+1...j]
		 * are splits
		 */
		private int split = -1;
		
		private boolean isTiledOrWord() {
			return isWord || isTiled;
		}
		
		@Override
		public String toString() {
			return "[iW=" + isWord + ",iT=" + isTiled + ",s="+split+"]";
		}
		
	}
	
	public static void main(String[] args) {
		Set<String> dictionary = new HashSet<String>();
		dictionary.add("be");
		dictionary.add("an");
		dictionary.add("at");
		dictionary.add("bed");
		dictionary.add("bath");
		dictionary.add("bat");
		dictionary.add("hand");
		dictionary.add("and");
		dictionary.add("hand");
		dictionary.add("beyond");
		dictionary.add("bean");
//		dictionary.add("an");
		
//		isTiledString("bean", dictionary);
		isTiledString("bedbathandbeyond", dictionary);
	}
	
	
	
	
	
	
	
	
	
}
