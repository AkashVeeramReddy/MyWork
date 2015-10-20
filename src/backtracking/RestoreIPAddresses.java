package backtracking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * https://leetcode.com/problems/restore-ip-addresses/
 * 
 * Given a string containing only digits, restore it by returning all possible
 * valid IP address combinations.
 * 
 * For example: Given "25525511135",
 * 
 * return ["255.255.11.135", "255.255.111.35"]. (Order does not matter)
 * 
 * @author user
 * 
 */
public class RestoreIPAddresses {
	public static List<String> restoreIpAddresses(String s) {
		List<String> ips = new ArrayList<String>();
        char[] charArray = s.toCharArray();
        String[] ip = new String[4];
        restoreIpAddresses(charArray,0,ip,0,-1,ips,0);
        return ips;
    }

	private static void restoreIpAddresses(char[] charArray, int charArrayIdx,
			String[] ip, int ipIdx, int partOfIpSeen,List<String> ips,int partOfIpSeenLength) {
		if((charArrayIdx == charArray.length)) {
			if((charArrayIdx == charArray.length) && (ipIdx == ip.length-1)
					&& (partOfIpSeen<=255) && (partOfIpSeenLength<=3)) {
				ip[3] = partOfIpSeen + "";
				StringBuilder builder = new StringBuilder();
				for (int i=0;i<4;i++) {
					builder.append(ip[i]);
					if(i!=3)
						builder.append(".");
				}
				ips.add(builder.toString());
			}
			return;
		}
		if(ipIdx == ip.length) {
			return;
		}
		char ch = charArray[charArrayIdx];
		int noAtIdx = (ch - '0');
		if(partOfIpSeen == -1) {
			restoreIpAddresses(charArray,charArrayIdx+1,ip,ipIdx,noAtIdx,ips,1);
		} else if(partOfIpSeen == 0) {
			ip[ipIdx]=partOfIpSeen + "";
			restoreIpAddresses(charArray,charArrayIdx+1,ip,ipIdx+1,noAtIdx,ips,1);
		} else if(partOfIpSeen <= 255 && partOfIpSeenLength<=3) {
			ip[ipIdx]=partOfIpSeen + "";
			restoreIpAddresses(charArray,charArrayIdx+1,ip,ipIdx+1,noAtIdx,ips,1);
			if(partOfIpSeenLength<3)
				restoreIpAddresses(charArray,charArrayIdx+1,ip,ipIdx,partOfIpSeen*10+noAtIdx,ips,partOfIpSeenLength+1);
		} else {
			//partOfIpSeen > 255. So not possible
			return;
		}
	}
	
	public static void main(String[] args) {
		String str = "010010";
		//String str = "1234";
		List<String> restoreIpAddresses = restoreIpAddresses(str);
		System.out.println(restoreIpAddresses);
	}
}
