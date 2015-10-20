package online.codejam.oct15;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;

import utils.FileUtils;

public class GFiles {

	
public static final String NEW_LINE = System.getProperty("line.separator");
	
	public static void main(String[] args) {
		boolean readFromFile = true;
		Scanner scanner = null;
		if(readFromFile) {
			File file = FileUtils.getFile("gfile.txt", GFiles.class);
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
			
			builder.append("Case #" + (i+1) +": ");
			
			dim = scanner.nextInt();
			//queries = scanner.nextInt();
			long val[] = new long[dim];
			int perc[] = new int[dim];
			for (int j = 0; j < dim; j++) {
				perc[j] = scanner.nextInt();
				val[j] = scanner.nextLong();
			}
			long est = canEstimate(perc, val);
			builder.append(est);
			builder.append(NEW_LINE);
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
	
	public static long canEstimate(int []percent, long []val) {
		int len = percent.length;
		if(percent[len-1] == 100) {
			return val[len-1];
		}
		long minVal = Long.MIN_VALUE;
		long maxVal = Long.MAX_VALUE;
		long curMinVal;
		long curMaxVal;
		for (int i = 0; i < val.length; i++) {
			if(percent[i] != 0) {
				curMinVal = (val[i]*100)/percent[i];
				if(((val[i]) % percent[i]) != 0)
					curMinVal++;
				/*if(((val[i] + 1) % percent[i]) == 0) {
					curMaxVal = ((val[i]+1)*100)/percent[i] - 1;
				} else {
					//curMaxVal = (long)((double)((val[i]+1)*100)/(double)percent[i]);
					curMaxVal = ((val[i]+1)*100)/percent[i];
				}*/
				curMaxVal = ((val[i]+1)*100)/percent[i];
				if(((val[i] + 1) % percent[i]) == 0)
					curMaxVal--;
				System.out.println("val "+val[i]+",perc "+percent[i]+",curMinVal+"+curMinVal+",curMaxVal+"+curMaxVal);
				//minVal = Math.max(curMinVal,minVal);
				//maxVal = Math.min(curMaxVal, maxVal);
				if(minVal<=curMinVal && curMinVal<=maxVal) {
					//overlapping interval
				} else if(curMinVal<=minVal && minVal<=curMaxVal) {
					//overlapping interval
				} else {
					//non overlapping
					return -1;
				}
				minVal = Math.max(curMinVal,minVal);
				maxVal = Math.min(curMaxVal, maxVal);
				System.out.println("curMinVal:"+curMinVal+",curMaxVal:"+curMaxVal);
			}
			
			
			
		}
		if(minVal > maxVal) {
			return -1;
		}
		minVal = Math.min(minVal, 2000);
		maxVal = Math.min(maxVal, 2000);
		long noOfSoln = 0;
		int itr;
		long solnVal = -1;
		for (long i = minVal; i <= maxVal; i++) {
			boolean isPoss = true;
			for (int j = 0; j < val.length; j++) {
				//itr = (long) (((double)(i*percent[j]))/100.0);
				itr = (int) ((val[j]*(long)100)/(long)i);
				if(itr != percent[j]) {
					isPoss = false;
					break;
				}
			}
			if(isPoss) {
				solnVal = i;
				noOfSoln++;
				if(noOfSoln > 1) {
					return -1;
				}
			}
		}
		return (solnVal);
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
