package online.codejam.oct15;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

import utils.FileUtils;

public class GRank {
	
public static final String NEW_LINE = System.getProperty("line.separator");
public static int maxComp;

	public static void main(String[] args) {
		boolean readFromFile = true;
		Scanner scanner = null;
		if(readFromFile) {
			File file = FileUtils.getFile("A-large.in", GRank.class);
			try {
				scanner = new Scanner(file);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		} else {
			scanner = new Scanner(System.in);
		}
		int noCases = scanner.nextInt();
		//int dim = 0, queries=0;
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < noCases; i++) {
			
			builder.append("Case #" + (i+1) +":");
			builder.append(NEW_LINE);
			String ranking = getRanking(scanner);
			builder.append(ranking);
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
	
	public static String getRanking(Scanner sc) {
		int numTopPlaces = sc.nextInt();
		int[] ranking = new int[numTopPlaces];
		for (int i = 0; i < ranking.length; i++) {
			ranking[i] = sc.nextInt();
		}
		int numComp = sc.nextInt();
		int []weightComp = new int[numComp];
		String[][] winnerComp = new String[numComp][numTopPlaces];
		for (int i = 0; i < numComp; i++) {
			weightComp[i] = sc.nextInt();
			String[] winners = new String[numTopPlaces];
			for (int j = 0; j < numTopPlaces; j++) {
				winners[j] = sc.next();
			}
			winnerComp[i] = winners;
		}
		maxComp = sc.nextInt();
		Map<String, Info> playerInfo = new HashMap<String, Info>();
		String podiumGuy;
		for (int i = 0; i < numComp; i++) {
			for (int j = 0; j < numTopPlaces; j++) {
				podiumGuy = winnerComp[i][j];
				Info info;
				info = playerInfo.get(podiumGuy);
				if(!playerInfo.containsKey(podiumGuy)) {
					info = new Info();
					info.name = podiumGuy;
					playerInfo.put(podiumGuy, info);
				}
				info.game.addPoint(weightComp[i]*ranking[j]);
			}
		}
		Collection<Info> values = playerInfo.values();
		Info[] array = values.toArray(new Info[]{});
		for (Info info : array) {
			info.game.initPoints();
		}
		Arrays.sort(array);
		System.out.println(Arrays.toString(array));
		StringBuilder builder = new StringBuilder();
		int prevRanking = 1,curRanking =0;
		long prevScore = 0,curScore;
		for (int i = 0; i < array.length; i++) {
			curScore = array[i].game.points;
			if(i==0) {
				curRanking = 1;
			} else {
				if(prevScore == curScore) {
					curRanking = prevRanking;
				} else {
					curRanking = i+1;
				}
			}
			builder.append(curRanking);
			prevScore = curScore;
			prevRanking = curRanking;
			builder.append(": ");
			builder.append(array[i].name);
			builder.append(NEW_LINE);
		}
		return builder.toString();
	}
	
	public static class Info implements Comparable<Info> {
		public String name;
		public GamePoint game = new GamePoint();
		
		@Override
		public int compareTo(Info o) {
			int compareTo = o.game.compareTo(game);
			if(compareTo == 0) {
				return -o.name.compareTo(name);
			}
			return compareTo;
		}
		
		@Override
		public String toString() {
			return name + " :"+game.points;
		}
		
	}
	
	public static class GamePoint implements Comparable<GamePoint> {
		public int numComp = 0;
		public long points = 0;
		public TreeMap<Integer, Integer> pointsVsCount = new TreeMap<Integer, Integer>();

		@Override
		public int compareTo(GamePoint o) {
			return Long.compare(points,o.points);
		}
		
		public void addPoint(int point) {
			if(numComp < maxComp) {
				numComp++;
				Integer integer = pointsVsCount.get(point);
				if (integer == null) {
					integer = 0;
					pointsVsCount.put(point, 1);
				}
				integer++;
				pointsVsCount.put(point, integer);
			} else {
				Entry<Integer, Integer> firstEntry = pointsVsCount.firstEntry();
				if(firstEntry.getKey() < point) {
					if(firstEntry.getValue() == 1) {
						pointsVsCount.remove(firstEntry.getKey());
					} else {
						firstEntry.setValue(firstEntry.getValue() - 1);
					}
					pointsVsCount.put(point, 1);
				}
			}
		}
		
		public void initPoints() {
			Set<Entry<Integer,Integer>> entrySet = pointsVsCount.entrySet();
			for (Entry<Integer, Integer> entry : entrySet) {
				points = points + entry.getKey()*entry.getValue();
			}
		}
	}
}
