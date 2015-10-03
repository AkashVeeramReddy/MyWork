package dynamicprogramming;

import java.io.FileNotFoundException;
import java.util.Scanner;

import utils.FileUtils;
//https://www.hackerrank.com/challenges/substring-diff/
public class SubstringDiff {
	public static void main(String[] args) {
		Scanner sc = null;
		boolean inputFile = true;
		if(inputFile) {
			try {
				sc = new Scanner(FileUtils.getFile("SubstringDiff.txt", SubstringDiff.class));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		} else {
			sc = new Scanner(System.in);
		}
		int numTC = sc.nextInt();
		for (int i = 0; i < numTC; i++) {
			System.out.println(getMaxLen(sc.nextInt(), sc.next().toCharArray(), sc.next().toCharArray()));
		}
	}
	
	public static int getMaxLen(int maxDiff, char[]charArr1, char[]charArr2) {
		int diff = 0;
		int len = charArr1.length;
		Info[][] infoArr = new Info[len+1][len+1];
		infoArr[0][0] = new Info();
		for (int i = 1; i <=len; i++) {
			infoArr[0][i] = new Info();
			infoArr[i][0] = new Info();
		}
		for (int i = 1; i <len; i++) {
			for (int j = 1; j <len; j++) {
				char ch1 = charArr1[i];
				char ch2 = charArr2[j];
				Info info = new Info();
				//Info leftInfo = infoArr[i][j-1];
				//Info rightInfo = infoArr[i-1][j];
				Info bothInfo = infoArr[i-1][j-1];
				if(ch1 == ch2) {
					//info.maxLenIdx = Math.max(le, b)
					//int candidate = bothInfo.maxLenIdx + 1;
					info.maxDiffIdx = bothInfo.maxDiffIdx;
					info.maxLenIdx = bothInfo.maxLenIdx + 1;
					info.idxWhereCharsDiff = bothInfo.idxWhereCharsDiff;
				} else {
					//increase maxDiffIdx
					int maxDiffItr = bothInfo.maxDiffIdx + 1;
					if(maxDiffItr > maxDiff) {
						info.maxDiffIdx = maxDiff;
						info.maxLenIdx = bothInfo.maxLenIdx - bothInfo.idxWhereCharsDiff;
						//info.idxWhereCharsDiff = 
					} else {
						info.maxDiffIdx = maxDiffItr;
						info.maxLenIdx = bothInfo.maxLenIdx + 1;
						info.idxWhereCharsDiff = bothInfo.idxWhereCharsDiff;
					}
				}
				infoArr[i+1][j+1] = info;
			}
		}
		return diff;
	}
	/**
	 * maximize length while keeping diff min
	 * @author user
	 *
	 */
	public static class Info {
		//public int maxDiffSoFar;
		//public int maxLenSoFar;
		
		public int maxDiffIdx;
		public int maxLenIdx;
		//idx in substring
		public int idxWhereCharsDiff;
	}
}
