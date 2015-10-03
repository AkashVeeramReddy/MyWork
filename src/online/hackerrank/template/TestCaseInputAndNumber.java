package online.hackerrank.template;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import utils.FileUtils;

/**
 * numTC - number of testcase
 * n-num1 for a test case
 * m - num2 for a test case
 * @author user
 *
 */
public class TestCaseInputAndNumber {
	public static void main(String[] args) {
		boolean readFromFile = true;
		Scanner  sc = null;
		if(readFromFile) {
			File file = FileUtils.getFile("TestCaseInputAndNumber.txt", TestCaseInputAndNumber.class);
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
		int num1,num2;
		for (int i = 0; i < numTC; i++) {
			num1 = sc.nextInt();
			num2 = sc.nextInt();
		}
	}
}
