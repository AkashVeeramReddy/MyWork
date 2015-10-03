package dynamicprogramming;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 
 * @author user
 *
 */
public class SherlockAndCost {
	public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc = new Scanner(System.in);
        int numTC = sc.nextInt();
        int size;
        for(int i=0;i<numTC;i++) {
            size = sc.nextInt();
            int []array = new int[size];
            for(int j=0;j<size;j++) {
                array[j] = sc.nextInt();
            }
            System.out.println(Arrays.toString(array));
        }
    }
    
    public static long getMaxDiff(int []array) {
        long maxDiff = 0;
        int size = array.length;
        Info[][] infoArr = new Info[size][size];
        for(int i=0;i<size;i++) {
            Info info = new Info();
            infoArr[i][i] = info;
        }
        for (int lenItr = 2; lenItr <=size; lenItr++) {
            for(int startIdx = 0; startIdx <=(size - lenItr + 1);startIdx++) {
                int endIdx = lenItr - startIdx + 1;
                for(int splitIdx = startIdx; splitIdx < endIdx; splitIdx++) {
                    Info leftInfo = infoArr[startIdx][splitIdx];
                    Info rightInfo = infoArr[splitIdx][endIdx];
                    int lengthLeft = splitIdx - startIdx + 1;
                    int lengthRight = endIdx - splitIdx;
                    if((lengthLeft == 1) && (lengthRight == 1)) {
                        
                    } else if(lengthLeft == 1) {
                        
                    } else if(lengthRight == 1) {
                        
                    } else {
                        //startIdx min, splitIdx min
                    	
                    }
                }
            }
        } 
        return maxDiff;
    }
    
    public static class Info {
        long maxDiffLeftMinRightMin;
        long maxDiffLeftMinRightMax;
        long maxDiffLeftMaxRightMin;
        long maxDiffLeftMaxRightMax;
    }
}
