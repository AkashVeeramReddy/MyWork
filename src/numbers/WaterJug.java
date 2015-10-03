package numbers;

import java.util.Scanner;
import utils.NumberUtils;

/**
 * @see WaterJug.pdf
 * @author user
 *
 */
public class WaterJug {
	public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc = new Scanner(System.in);
        int numTC = sc.nextInt();
        for(int i=0;i<numTC;i++) {
            int jug1 = sc.nextInt();
            int jug2 = sc.nextInt();
            int target = sc.nextInt();
            if(target > Math.max(jug1,jug2)) {
                System.out.println("NO");
                continue;
            }
            int gcd = NumberUtils.getGCD(jug1,jug2);
            if(target % gcd == 0) {
                 System.out.println("YES");
            } else {
                 System.out.println("NO");
            }
        }
    }
}
