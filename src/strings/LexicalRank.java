package strings;


/**
 * 
 * Lexicographic ran  k of a string
Given a string, find its rank among all its permutations sorted lexicographically. For example, rank of “abc” is 1, rank of “acb” is 2, and rank of “cba” is 6.

For simplicity, let us assume that the string does not contain any duplicated characters.
 * @author user
 *
 */
public class LexicalRank {
	public static void main(String[] args) {
		long lexicographicalRank = getLexicographicalRankDuplicates("ababc");
		System.out.println(lexicographicalRank);
	}
	public static long getLexicographicalRankNoDuplicate(String str) {
		int len = str.length();
		char[] charArray = str.toCharArray();
		int []count = new int[26];
		for (int i = 0; i < charArray.length; i++) {
			count[charArray[i] - 'a']++;
		}
		for (int i = 0; i < 26; i++) {
			count[i] = count[i] + (i==0?0:count[i-1]);
		}
		long rank = 0;
		long pow = 1;
		long[] factArray = new long[len+1];
		factArray[len] = 0;
		factArray[len - 1] = 1;
		for (int i = 2; i < factArray.length; i++) {
			factArray[len - i] = factArray[len - i + 1]*i;
		}
		
		for (int i = 0; i < len ; i++) {
			int rankCh = count[charArray[i] - 'a'] - 1;
			rank = rank + factArray[i+1]*rankCh;
			for (int j = charArray[i] - 'a'; j < 26; j++) {
				if(count[j] > 1)
					count[j]--;
			}
		}
		return rank+1;
	}
	public static long getLexicographicalRankDuplicates(String str) {
		int len = str.length();
		char[] charArray = str.toCharArray();
		Info []infoArray = new Info[26];
		for (int i = 0; i < 26; i++) {
			infoArray[i] = new Info();
		}
		for (int i = 0; i < charArray.length; i++) {
			infoArray[charArray[i] - 'a'].occ++;
		}
		for (int i = 0; i < 26; i++) {
			infoArray[i].rank = ((i==0)?0:(infoArray[i-1].rank + infoArray[i-1].occ));
			//infoArray[i].rank2 =  ((i==0)?0
		}
		long[] factArray = new long[len+1];
		factArray[len] = 0;
		factArray[len - 1] = 1;
		for (int i = 2; i < factArray.length; i++) {
			factArray[len - i] = factArray[len - i + 1]*i;
		}
		long rank = 0;
		for (int i = 0; i < len ; i++) {
			int rankCh = infoArray[charArray[i] - 'a'].rank;
			infoArray[charArray[i] - 'a'].occ--;
			for (int j = charArray[i] - 'a' + 1; j < 26; j++) {
				if(infoArray[j].rank > 0)
					infoArray[j].rank--;
			}
			long longDivFactor = 1;
			/**
			 * TODO
			 * change reqd here
			 */
			for (int k = 0; k < charArray[i] - 'a'; k++) {
				int occ = infoArray[k].occ;
				if(occ > 1) {
					longDivFactor = longDivFactor*factArray[len - occ];
				}
				rank = rank + (factArray[i+1]/longDivFactor)*rankCh;
			}
		}
		//System.out.println(Arrays.toString(infoArray));
		return rank+1;
	}
	public static class Info {
		public int rank = 0;
		public int occ = 0;
		public int rank2 = 0;
		@Override
		public String toString() {
			return "[r=" + rank + ",o=" + occ + "]";
		}
		
	}
}
