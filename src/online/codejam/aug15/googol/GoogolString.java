package online.codejam.aug15.googol;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;

import utils.FileUtils;

public class GoogolString {
	
	public static final String NEW_LINE = System.getProperty("line.separator");
	
	public static void main(String[] args) {
		boolean readFromFile = true;
		Scanner scanner = null;
		if(readFromFile) {
			File file = FileUtils.getFile("A-large.in", GoogolString.class);
			try {
				scanner = new Scanner(file);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		} else {
			scanner = new Scanner(System.in);
		}
		int noCases = scanner.nextInt();
		long idx;
		//int inputSize = 0;
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < noCases; i++) {
			idx = scanner.nextLong();
			long output = getOutput(idx, i);
			builder.append("Case #");
			builder.append((i+1));
			builder.append(":");
			builder.append(" ");
			builder.append(output);
			builder.append(NEW_LINE);
		}
		System.out.println(builder);
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
	
	public static long getOutput(long idx,long caseNo) {
		long prevPower = 1;
		long power2 = 1;
		long itr=0;
		for(;power2<=idx; itr++,prevPower=power2,power2=power2*2) {
			
		}
		System.out.println("idx "+idx+",n "+itr+",power2 "+power2);
		return getAns(idx, itr, power2*2,power2);
		//return "";
	}
	
	public static long getAns(long idx,long n,long twoRaisedN,long twoRaisedNMinus1) {
		if(idx == 1 || idx == 2) {
			return 0;
		}
		if(idx == twoRaisedNMinus1) {
			return 0;
		} else if(idx < twoRaisedNMinus1) {
			return getAns(idx, n-1, twoRaisedNMinus1, twoRaisedNMinus1/2);
		} else {
			//idx > twoRaisedNMinus1
			long diff = twoRaisedN - idx;
			return (getAns(diff, n-1, twoRaisedNMinus1, twoRaisedNMinus1/2) + 1)%2;
		}
		//return -1;
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
