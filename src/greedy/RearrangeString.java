package greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;


/**
 * http://www.geeksforgeeks.org/rearrange-a-string-so-that-all-same-characters-
 * become-at-least-d-distance-away/
 * 
 * Given a string and a positive integer d. Some characters may be repeated in
 * the given string. Rearrange characters of the given string such that the same
 * characters become d distance away from each other. Note that there can be
 * many possible rearrangements, the output should be one of the possible
 * rearrangements. If no such arrangement is possible, that should also be
 * reported. Expected time complexity is O(n) where n is length of input string.
 * Examples:
	Input:  "abb", d = 2
	Output: "bab"
	
	Input:  "aacbbc", d = 3
	Output: "abcabc"
	
	Input: "geeksforgeeks", d = 3
	Output: egkegkesfesor
	
	Input:  "aaa",  d = 2
	Output: Cannot be rearranged
 * 
 * @author user
 * 
 */
public class RearrangeString {
	public static void main(String[] args) {
		String rearrangeString = rearrangeString("geeksforgeeks", 3);
		System.out.println(rearrangeString);
	}
	public static String rearrangeString(String str, int k) {
		char[] charArray = str.toCharArray();
		int []array = new int[26];
		for (int i = 0; i < charArray.length; i++) {
			char c = charArray[i];
			array[c-97] = array[c-97] + 1;
			
		}
		//System.out.println(Arrays.toString(array));
		List<Info> list = new ArrayList<Info>();
		for (int i = 0; i < array.length; i++) {
			if(array[i] != 0) {
				Info info = new Info();
				info.occ = array[i];
				info.ch = (char) (97 + i);
				list.add(info);
			}
		}
		PriorityQueue<Info> maxHeap = new PriorityQueue<Info>(list);
		Info poll = maxHeap.poll();
		char result[] = new char[str.length()];
		int startPos = 0;
		int insertPos;
		while(poll != null) {
			int occ = poll.occ;
			for(int i=0;i<occ;i++) {
				insertPos = startPos + i*k;
				if(insertPos < str.length()) {
					result[insertPos] = poll.ch;
				} else {
					return null;
				}
			}
			poll = maxHeap.poll();
			for(int i=startPos+1;i<str.length();i++) {
				if(result[i] == 0) {
					startPos = i;
					break;
				}
			}
		}
		System.out.println(maxHeap);
		return new String(result);
	}
	
	public static class Info implements Comparable<Info> {
		public char ch;
		public int occ;
		@Override
		public String toString() {
			return "[ch=" + ch + ", occ=" + occ + "]";
		}
		@Override
		public int compareTo(Info o) {
			int normalCmp;
			normalCmp = ((occ==o.occ)?Character.compare(ch, o.ch):Integer.compare(occ, o.occ));
			//for maxHeap
			return -normalCmp;
		}
		
		
	}
}
