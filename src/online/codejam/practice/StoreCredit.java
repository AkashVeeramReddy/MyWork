package online.codejam.practice;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;

import utils.FileUtils;

public class StoreCredit {
	public static final String NEW_LINE = System.getProperty("line.separator");
	
	public static void main(String[] args) {
		boolean readFromFile = true;
		Scanner scanner = null;
		if(readFromFile) {
			File file = FileUtils.getFile("storeCreditLarge.in", StoreCredit.class);
			try {
				scanner = new Scanner(file);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		} else {
			scanner = new Scanner(System.in);
		}
		int noCases = scanner.nextInt();
		int credit;
		int inputSize = 0;
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < noCases; i++) {
			credit = scanner.nextInt();
			inputSize = scanner.nextInt();
			Integer [] array = new Integer[inputSize];
			for (int j = 0; j < array.length; j++) {
				array[j] = scanner.nextInt();
			}
			builder.append(printItemIndices(array, credit, i));
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
	
	public static String printItemIndices(Integer[] array,int credit,int caseNo) {
		//System.out.println(Arrays.toString(array));
		Integer[] info = new Integer[credit];
		int itr;
		for (int i = 0; i < array.length; i++) {
			itr = array[i];
			if(itr < credit) {
				Integer infoFirst = info[itr];
				boolean isCheckReqd = true;
				if(infoFirst != null) {
					isCheckReqd = ((credit %2 == 0) && (itr == (credit/2)));
				} else {
					//info[itr] = i;
					
				}
				//info[itr] = i;
				if(isCheckReqd) {
				Integer infoAt = info[credit-itr];
					if(infoAt != null && infoAt !=i) {
						StringBuilder stringBuilder = new StringBuilder();
						stringBuilder.append("Case #");
						stringBuilder.append((caseNo+1));
						stringBuilder.append(": ");
						stringBuilder.append((infoAt+1));
						stringBuilder.append(" ");
						stringBuilder.append((i+1));
						//System.out.println(stringBuilder.toString());
						return stringBuilder.toString();
					}
				}
				info[itr] = i;
			}
		}
		return "";
	}
}
