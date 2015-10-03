package numbers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;

import utils.FileUtils;

/**
 * We have to maintain a stack of discs in which smaller disk should be kept on larger block.
 * 
 * If a disc cannot be placed in a stack a new stack is to be created having the disc.
 * 
 * Given an array arr[] having n discs each radius is given in array, if Mehuls scans for a place to 
 * keep the disk whose top most disk is the smallest but still can accomodate it
 * , how many stacks will be required and what will be the radius of disc on the top
 * @author user
 *
 */
public class MehulStack {
	public static void main(String[] args) {
		
		boolean readFromFile = true;
		Scanner  sc = null;
		if(readFromFile) {
			File file = FileUtils.getFile("MehulStack.txt", MehulStack.class);
			try {
				sc = new Scanner(file);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		} else {
			sc = new Scanner(System.in);
		}
		int numTC = sc.nextInt();
		for(int i=0;i<numTC;i++) {
			int size = sc.nextInt();
			int arr[] = new int[size];
			for(int j=0;j<size;j++) {
				arr[j] = sc.nextInt();
			}
			System.out.println(Arrays.toString(arr));
			List<Info> topStacks = getTopStacks(arr);
			System.out.print(topStacks.size());
			System.out.print(" ");
			for (Info info : topStacks) {
				System.out.print(info.topRadius);
				System.out.print(" ");
			}
			System.out.println();
		}
	}
	
	public static List<Info> getTopStacks(int []arr) {
		TreeSet<Info> infoMap = new TreeSet<Info>();
		List<Info> infoList = new ArrayList<Info>();
		int ele;
		int noOfStacks = 0;
		for(int i=0;i<arr.length;i++) {
			ele = arr[i];
			Info dummyInfo = new Info();
			dummyInfo.no = Integer.MIN_VALUE;
			dummyInfo.topRadius = ele + 1;
			Info higher = infoMap.higher(dummyInfo);
			if(higher == null) {
				noOfStacks++;
				dummyInfo.no = noOfStacks;
				dummyInfo.topRadius = ele;
				infoMap.add(dummyInfo);
				infoList.add(dummyInfo);
			} else {
				infoMap.remove(higher);
				higher.topRadius = ele;
				infoMap.add(higher);
			}
		}
		return infoList;
	}
	public static class Info implements Comparable<Info> {
		Integer no;
		Integer topRadius;
		@Override
		public int compareTo(Info o) {
			int compareTo =  topRadius.compareTo(o.topRadius);
			if(compareTo == 0) {
				return no.compareTo(o.no);
			}
			return compareTo;
		}
		
	}
	
}
