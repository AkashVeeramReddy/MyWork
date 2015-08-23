package online.codejam.aug15.gcube;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.Scanner;

import utils.FileUtils;

public class GCube {
	
	public static final String NEW_LINE = System.getProperty("line.separator");
	
	public static void main(String[] args) {
		boolean readFromFile = true;
		Scanner scanner = null;
		if(readFromFile) {
			File file = FileUtils.getFile("B-large-practice.in", GCube.class);
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
			long []array = new long[dim];
			for (int j = 0; j < dim; j++) {
				array[j] = scanner.nextLong();
			}
			System.out.println(Arrays.toString(array));
			ProductInfo info = new ProductInfo(array);
			System.out.println(info);
			int low = 0,high = 0;
			for (int queryItr = 0; queryItr < queries; queryItr++) {
				low = scanner.nextInt();
				high = scanner.nextInt();
				System.out.println(low + " " + high);
				if(low == high) {
					System.out.println(array[low]);
					builder.append(array[low]);
				} else {
					Info findProduct = info.findProduct(low, high);
					double nthRoot = getNthRoot(findProduct, high - low + 1);
					String string = Double.toString(nthRoot);
					if(string.startsWith("Infi")) {
						System.out.print("");
					}
					System.out.println(nthRoot);
					builder.append(nthRoot);
				}
				builder.append(NEW_LINE);
			}
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
	
	public static double getNthRoot(Info info,int n) {
		double arg = 1.0/((long)n);
		double product = info.product;
		long powerOf10 = info.powerOf10;
		/*
		long mod = powerOf10 % n;
		long div = powerOf10 / n;
		for (int itr = 1; itr <= mod; itr++) {
			product = product * 10;
		}
		double pow = Math.pow(product, arg);
		for (int itr = 1; itr <= div; itr++) {
			pow = pow * 10;
		}
		return pow;
		*/
		return Math.pow(product,arg) *
				Math.pow(10, powerOf10*arg);
	}
	
	public static class ProductInfo {
		
		
		protected Info[] segmentTree;
		//no of elements(not in segment tree)
		protected int noOfElements;
		//protected Integer[] actualArray;
		
		public ProductInfo(long []array) {
			//actualArray = Arrays.copyOf(array, array.length);
			noOfElements = array.length;
			//size reqd is 2*(2 ^ Ceil(log2(n))) - 1
			double logNoOfEle = log2(noOfElements);
			double ceil = Math.ceil(logNoOfEle);
			double pow = Math.pow(2, ceil);
			pow = pow*2;
			segmentTree = new Info[(int)pow - 1];
			populateSegmentTree(array);
		}
		
		protected void populateSegmentTree(long[] array) {
			populateSegmentTree(array,0,array.length - 1,0);
		}
		
		protected int getMidPoint(int start,int end) {
			return start + (end - start)/2;
		}
		
		protected int getParentIdx(int idx) {
			return (idx + 1)/2 - 1;
		}
		
		protected int getLeftChildIdx(int idx) {
			return 2*idx + 1;
		}
		
		protected int getRightChildIdx(int idx) {
			return 2*idx + 2;
		}
		
		public static Info getInfoForNumber(long no) {
			long power10 = 0;
			double val = no;
			while(val>10) {
				val = val/10;
				power10++;
				
			}
			Info info = new Info();
			info.powerOf10 = power10;
			info.product = val;
			return info;
		}
		protected Info populateSegmentTree(long[] array, int start, int end,int idx) {
			if(start == end) {
				//leaf
				Info infoForNumber = getInfoForNumber(array[start]);
				segmentTree[idx] = infoForNumber;
				return infoForNumber;
			} else {
				int mid = getMidPoint(start, end);
				int leftSumIdx = getLeftChildIdx(idx);
				Info leftInfo = populateSegmentTree(array, start, mid, leftSumIdx);
				
				int rightSumIdx = getRightChildIdx(idx);
				Info rightInfo = populateSegmentTree(array, mid+1, end, rightSumIdx);
				
				Info info = new Info();
				double leftProd = leftInfo.product;
				double rightProd = rightInfo.product;
				double product;
				long power10;
				if(leftProd*rightProd < 10) {
					product = leftProd*rightProd;
					power10 = leftInfo.powerOf10 + rightInfo.powerOf10;
				} else {
					product = (leftProd*rightProd)/10;
					power10 = leftInfo.powerOf10 + rightInfo.powerOf10 + 1;
				}
				info.product = product;
				info.powerOf10 = power10;
				segmentTree[idx] = info;
				return info;
			}
		}
		
		@Override
		public String toString() {
			return " ST = " + Arrays.toString(segmentTree);
		}
		
		public static double log2(double x) {
		     return Math.log(x)/Math.log(2.0d);
		}
		
		public Info findProduct(int lower,int higher) {
			//check range
			if(lower >=0 && higher<noOfElements) {
				//return findSum(sumIdx,lower, higher);
				return findProduct(0, 0, noOfElements-1, lower, higher);
			}
			return null;
		}
		
		public Info findProduct(int sumIdx, int lowerForSumIdx,int higherForSumIdx,
				int lower,int higher) {
			if(lowerForSumIdx >= lower && higherForSumIdx <= higher) {
				//within range
				return segmentTree[sumIdx];
			} else {
				//range complete mismatch
				if(lowerForSumIdx > higher || higherForSumIdx < lower) {
					Info info = new Info();
					info.powerOf10 = 0;
					info.product = 1;
					return info;
				} else {
					//some range overlap
					int leftSumIdx = getLeftChildIdx(sumIdx);
					int rightSumIdx = getRightChildIdx(sumIdx);
					int mid = getMidPoint(lowerForSumIdx, higherForSumIdx);
					
					Info leftInfo = findProduct(leftSumIdx, lowerForSumIdx, mid, lower, higher);
					Info rightInfo = findProduct(rightSumIdx, mid+1, higherForSumIdx, lower, higher);
					
					Info info = new Info();
					double leftProd = leftInfo.product;
					double rightProd = rightInfo.product;
					double product;
					long power10;
					if(leftProd*rightProd < 10) {
						product = leftProd*rightProd;
						power10 = leftInfo.powerOf10 + rightInfo.powerOf10;
					} else {
						product = (leftProd*rightProd)/10;
						power10 = leftInfo.powerOf10 + rightInfo.powerOf10 + 1;
					}
					info.product = product;
					info.powerOf10 = power10;
					return info;
				}
				
			}
			
		}
		
		
	}
	
	public static class Info {
		//>=1 and < 9
		double product;
		long powerOf10;
		@Override
		public String toString() {
			return "[prod=" + product + ",pow=" + powerOf10
					+ "]";
		}
		
	}

}
