package online.codejam.sep15.travel;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;

import utils.FileUtils;

public class Travel {
	public static void main(String[] args) {
		boolean readFromFile = true;
		Scanner scanner = null;
		if(readFromFile) {
			File file = FileUtils.getFile("A-large.in", Travel.class);
			try {
				scanner = new Scanner(file);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		} else {
			scanner = new Scanner(System.in);
		}
		int noCases = scanner.nextInt();
		int noOfCities = 0, noOfRoads = 0,noOfQns = 0;
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < noCases; i++) {
			noOfCities = scanner.nextInt();
			noOfRoads = scanner.nextInt();
			noOfQns = scanner.nextInt();
			Graph graph = new Graph(noOfCities);
			for (int j = 0; j < noOfRoads; j++) {
				int v1 = scanner.nextInt();
				int v2 = scanner.nextInt();
				int times[] = new int[24];
				for (int k = 0; k < 24; k++) {
					times[k] = scanner.nextInt();
				}
				graph.addRoad(v1, v2, times);
			}
			builder.append("Case #");
			builder.append((i+1));
			builder.append(":");
			builder.append(" ");
			for (int l = 0; l < noOfQns; l++) {
				int dest = scanner.nextInt();
				int time = scanner.nextInt();
				graph.clearValues();
				long findDistance = graph.findDistance(dest, time);
				if(findDistance == Long.MAX_VALUE) {
					builder.append(-1);
				} else {
					builder.append(findDistance);
				}
				builder.append(" ");
				
			}
			builder.append("\n");
		}
		System.out.println("Done");
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
