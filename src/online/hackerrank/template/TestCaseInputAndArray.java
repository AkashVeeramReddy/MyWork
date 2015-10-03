package online.hackerrank.template;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import utils.FileUtils;
/**
 * Given n number of test cases. here n=3
 * For each test case, given m-size of array and then m elements here m=2 for test case 1
 * 
 * eg
 * 3
 *
 * 2 
 * 1 4
 * 
 * 5
 * 12345
 * 
 * 4
 * 1234
 * 
 * @author user
 *
 */
public class TestCaseInputAndArray {
	public static void main(String[] args) {
		boolean readFromFile = true;
		Scanner  sc = null;
		if(readFromFile) {
			File file = FileUtils.getFile("MehulStack.txt", TestCaseInputAndArray.class);
			try {
				sc = new Scanner(file);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		} else {
			sc = new Scanner(System.in);
		}
		//Scanner  sc = new Scanner(System.in);
		int numTC = sc.nextInt();
		int []arr;
		int numOfElements;
		for (int i = 0; i < numTC; i++) {
			numOfElements = sc.nextInt();
			arr = new int[numOfElements];
			for (int j = 0; j < numOfElements; j++) {
				arr[j] = sc.nextInt();
			}
		}
	}
}
