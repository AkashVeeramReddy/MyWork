package strings;

import java.util.Arrays;
import java.util.LinkedList;

import javax.print.DocFlavor.STRING;

import sun.awt.image.ImageWatched.Link;

/**
 * https://leetcode.com/problems/simplify-path/
 * 
 * Given an absolute path for a file (Unix-style), simplify it.
 * 
 * For example, path = "/home/", => "/home" path = "/a/./b/../../c/", => "/c"
 * 
 * Corner Cases: Did you consider the case where path = "/../"? In this case,
 * you should return "/". Another corner case is the path might contain multiple
 * slashes '/' together, such as "/home//foo/". In this case, you should ignore
 * redundant slashes and return "/home/foo".
 * 
 * @author user
 * 
 */
public class SimplifyPath {
	public static void main(String[] args) {
		String path = "/a/./b/../../c/";
		String simplifyPath = simplifyPath(path);
		System.out.println(simplifyPath);
	}
	public static String simplifyPath(String path) {
        String[] split = path.split("/");
        //System.out.println(Arrays.toString(split));
        LinkedList<String> strings = new LinkedList<String>();
        String str;
        for (int i = 0; i < split.length; i++) {
			str = split[i].trim();
			if(str.equals("..")) {
				if(!strings.isEmpty())
					strings.removeLast();
			} else if(str.equals(".")) {
				//do nothing
			} else if(str.equals("")) {
				//do nothing
			} else {
				strings.addLast(str);
			}
		}
        System.out.println(strings);
        StringBuilder builder = new StringBuilder();
        builder.append("/");
        for (String string : strings) {
			builder.append(string);
			builder.append("/");
		}
        if(!strings.isEmpty()) {
        	builder.setLength(builder.length()-1);
        }
        return builder.toString();
    }
}
