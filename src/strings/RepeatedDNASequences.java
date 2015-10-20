package strings;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * Repeated DNA Sequences
 * https://leetcode.com/problems/repeated-dna-sequences/
 * All DNA is composed of a series of nucleotides abbreviated as A, C, G, and T, for example: "ACGAATTCCG". When studying DNA, it is sometimes useful to identify repeated sequences within the DNA.

Write a function to find all the 10-letter-long sequences (substrings) that occur more than once in a DNA molecule.

For example,

Given s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT",

Return:
["AAAAACCCCC", "CCCCCAAAAA"].
 * @author user
 *
 */
public class RepeatedDNASequences {
	public static void main(String[] args) {
		String str = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT";
		//String str = "CCCCCCCCCCC";
		List<String> findRepeatedDnaSequences = findRepeatedDnaSequences(str);
		System.out.println(findRepeatedDnaSequences);
	}
	/*public static List<String> findRepeatedDnaSequences(String s) {
		List<String> strings = new ArrayList<String>();
		LinkedHashMap<String, Integer> subStrVsCount = new LinkedHashMap<String, Integer>();
		char[] charArray = s.toCharArray();
		String subStr;
		for (int i = 0; i < charArray.length-10; i++) {
			subStr = s.substring(i, i+10);
			Integer integer = subStrVsCount.get(subStr);
			if(integer == null)
				integer = 0;
			subStrVsCount.put(subStr, integer+1);
		}
		Set<Entry<String,Integer>> entrySet = subStrVsCount.entrySet();
		for (Entry<String, Integer> entry : entrySet) {
			if(entry.getValue() > 1)
				strings.add(entry.getKey());
		}
		return strings;
    }*/

	private static List<String> findRepeatedDnaSequences(String s) {
		//using rabin karp
		List<String> strings = new ArrayList<String>();
		char[]charArr = s.toCharArray();
		//int multFactor = 1 << 20;
		if(s.length() > 10) {
			//Map<Integer, Integer> hashVsCount = new LinkedHashMap<Integer, Integer>();
			//null means not present, false means 1,true means > 1 
			Boolean[] hashBool = new Boolean[1<<20];
			int hash = 0;
			for (int i = 0; i < 10; i++) {
				hash = (hash<<2) + getInt(charArr[i]);
			}
			hashBool[hash] = false;
			for (int i = 10; i < charArr.length; i++) {
				hash = (hash<<2) - (getInt(charArr[i-10]) << 20) + getInt(charArr[i]);
				Boolean integer = hashBool[hash];
				if(integer == null)
					integer = false;
				else
					integer = true;
				hashBool[hash] = integer;
			}
			//System.out.println(hashVsCount);
			//Set<Entry<Integer,Integer>> entrySet = hashVsCount.entrySet();
			for (int i=0;i<hashBool.length;i++) {
				Boolean count = hashBool[i];
				if(count == null || count == false)
					continue;
				Integer key = i;
				int noItr = 0;
				LinkedList<Character> characters = new LinkedList<Character>();
				int rem = 0;
				while(noItr < 10) {
					rem = key%4;
					characters.addFirst(getCh(rem));
					key = key >> 2;
					noItr++;
				}
				StringBuilder builder = new StringBuilder();
				for (Character character : characters) {
					builder.append(character);
				}
				strings.add(builder.toString());
			}
		}
		return strings;
	}
	
	public static int getInt(char ch) {
		switch (ch) {
		case 'A':
			return 0;
		case 'C':
			return 1;
		case 'T':
			return 2;
		case 'G':
			return 3;
		default:
			break;
		}
		return 0;
	}
	
	public static char getCh(int num) {
		switch (num) {
		case 0:
			return 'A';
		case 1:
			return 'C';
		case 2:
			return 'T';
		case 3:
			return 'G';
		default:
			break;
		}
		return 0;
	}
}
