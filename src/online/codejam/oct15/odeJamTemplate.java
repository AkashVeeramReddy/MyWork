package online.codejam.oct15;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;

import utils.FileUtils;

public class odeJamTemplate {
	
public static final String NEW_LINE = System.getProperty("line.separator");
	
	public static void main(String[] args) {
		boolean readFromFile = true;
		Scanner scanner = null;
		if(readFromFile) {
			File file = FileUtils.getFile("B-large-practice.in", odeJamTemplate.class);
			try {
				scanner = new Scanner(file);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		} else {
			scanner = new Scanner(System.in);
		}
		int noCases = scanner.nextInt();
		int dim = 0, queries=0;
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < noCases; i++) {
			
			builder.append("Case #" + (i+1) +":");
			builder.append(NEW_LINE);
			dim = scanner.nextInt();
			queries = scanner.nextInt();
		}
		String property = System.getProperty("user.home");
		File homeFolder = new File(property);
		File op = new File(homeFolder,"output.txt");
		try {
			if(!op.exists()) {
				op.createNewFile();
			}
			writeToFile(op, builder.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
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
