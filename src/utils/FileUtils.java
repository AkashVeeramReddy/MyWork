package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;

public class FileUtils {
	
	public static String getFileContents(File file) throws Exception {
		StringBuilder builder = new StringBuilder();
		BufferedReader br = null;
		try {
			br= new BufferedReader(new FileReader(file));
			String line;
		    while ((line = br.readLine()) != null) {
				builder.append(line);
				builder.append(MyConstants.NEW_LINE);
		    }
		} finally {
			if(br != null)
				br.close( );
		}
		return builder.toString();
	}
	
	public static List<String> getFileLines(File file) throws Exception {
		String fileContents = getFileContents(file);
		String[] split = PatternUtils.NEWLINE_PATTERN.split(fileContents);
		return Arrays.asList(split);
		
	}
	
	public static void writeToFile(File file, String contents) throws Exception {
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter( file));
			writer.write(contents);
		} finally {
			if(writer != null)
				writer.close( );
		}
	}
}
