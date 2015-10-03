package online.codejam.year2008;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Scanner;

import utils.FileUtils;

public class MinimumScalarProduct {
	public static void main(String[] args) {
		boolean readFromFile = true;
		Scanner scanner = null;
		if(readFromFile) {
			File file = FileUtils.getFile("A-large-practice.in", MinimumScalarProduct.class);
			try {
				scanner = new Scanner(file);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		} else {
			scanner = new Scanner(System.in);
		}
		int numTC = scanner.nextInt();
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < numTC; i++) {
			int dim = scanner.nextInt();
			int []array1 = new int[dim];
			for (int j = 0; j < dim; j++) {
				array1[j] = scanner.nextInt();
			}
			int []array2 = new int[dim];
			for (int j = 0; j < dim; j++) {
				array2[j] = scanner.nextInt();
			}
			BigInteger minScalar = getMinScalar(array1, array2);
			builder.append("Case #");
			builder.append((i+1));
			builder.append(": ");
			builder.append(minScalar);
			builder.append('\n');
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
	
	public static BigInteger getMinScalar(int []array1, int []array2) {
		BigInteger prod = new BigInteger("0");
		Arrays.sort(array1);
		Arrays.sort(array2);
		for (int i = 0; i < array1.length; i++) {
			BigInteger num1 = new BigInteger(Integer.toString(array1[i])); ;
			BigInteger num2 = new BigInteger(Integer.toString(array2[array1.length - i -1]));
			num1 = num1.multiply(num2);
			prod = prod.add(num1);
		}
		return prod;
	}
}
