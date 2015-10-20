package graphs;

import java.util.Scanner;

public class MinimumSizeClique {

    public static void main(String[] args) {
       /*
		boolean readFromFile = true;
		Scanner  sc = null;
		if(readFromFile) {
			File file = FileUtils.getFile("MinimumSizeClique.txt", MinimumSizeClique.class);
			try {
				sc = new Scanner(file);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		} else {
			sc = new Scanner(System.in);
		}*/
		Scanner  sc = new Scanner(System.in);
        int numTC = sc.nextInt();
        int node;
        int edge;
        for(int i=0;i<numTC;i++) {
            node = sc.nextInt();
            edge = sc.nextInt();
            if(edge == 0) {
                System.out.println(1);
            } else if(edge <=node) {
                System.out.println(2);
            } else if(edge == (node*(node-1))/2) {
                System.out.println(node);
            } else {
                long possEdges,maxClique=2;
                double square = Math.pow(node,2);
                for(int j=3;j<node;j++) {
                    //System.out.println("j "+j);
                    double r = j-1;
                    // System.out.println("r "+r);
                    double frac = (r - 1.0)/r;
                    // System.out.println("frac "+frac);
                    double val = (frac*square)/2.0;
                    //System.out.println("val "+val);
                    possEdges = (long)val;
                    //System.out.println("possEdges "+possEdges);
                    if(edge > possEdges) {
                        maxClique = j;
                    } else {
                        break;
                    }
                }
                System.out.println(maxClique);
            }
        }
    }
}