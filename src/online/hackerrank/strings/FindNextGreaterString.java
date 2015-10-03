package online.hackerrank.strings;

import java.util.Scanner;
/**
 * Given a word w, rearrange the letters of w to construct another word s in such a way that s is lexicographically greater than w.
 *  In case of multiple possible answers, find the lexicographically smallest one.
 *  See {@link FindNextGreaterString.pdf}
 * @author user
 *
 */
public class FindNextGreaterString {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc = new Scanner(System.in);
        int numTC = sc.nextInt();
        String str;
        for(int tcItr = 0; tcItr < numTC; tcItr++) {
            //System.out.print("TC "+(tcItr+1));
            str = sc.next();
            System.out.println(getNextStr(str));
        }
    }
    
    public static String getNextStr(String str) {
        int len = str.length();
        char[] chArr = str.toCharArray();
        boolean isChanged = false;
        for(int i=len-2; i>=0;i--) {
            if(chArr[i] >= chArr[i+1]) {
                //dont do anything
            } else {
                isChanged = true;
                //System.out.print(" i is "+i);
                //System.out.print(" ch is "+chArr[i]);
                int idx = findNextGreater(chArr,i+1,len-1,chArr[i]);
                //System.out.print(" NextGreater is "+idx);
                //System.out.print(" ");
                swapIdx(chArr,i,idx);
                //now reverse the array from i+1 to 0
                reverse(chArr,i+1,len-1);
                break;
                
            }
        }
        if(isChanged) {
            return new String(chArr);
        } else {
            return "no answer";
        }
        //return str;
    }
    //start - smaller end-larger
    public static int findNextGreater(char[] charArr, int start, int end, char ch) {
        if(start ==  end) {
            return start;
        } else if(start > end) {
            return -1;
        } else if((end - start) == 1) {
            //size 2
            if(charArr[end] > ch) {
                return end;
            } else {
                return start;
            }
            
        }  else {
            int mid = (start + end)/2;
            char midC = charArr[mid];
            if(midC > ch) {
                int midPlus1 = mid + 1;
                char chMidPlus1 = charArr[midPlus1];
                if(chMidPlus1 == ch) {
                    return mid;
                }
                return findNextGreater(charArr, mid,end,ch);
            } else if(midC < ch) {
                return findNextGreater(charArr, start,mid-1,ch);
            } else {
                //midC == ch
                return findNextGreater(charArr, start,mid-1,ch);
            }
        }
        
    }
    public static void swapIdx(char[] charArr, int idx1, int idx2) {
        char temp = charArr[idx1];
        charArr[idx1] = charArr[idx2];
        charArr[idx2] = temp;
    }
    
    //fromIdx-smaller endIdx-larger
    public static void reverse(char[] charArr, int fromIdx, int endIdx) {
        if(fromIdx < endIdx) {
            swapIdx(charArr, fromIdx, endIdx);
            reverse(charArr,fromIdx+1,endIdx-1);
        }
    }
}