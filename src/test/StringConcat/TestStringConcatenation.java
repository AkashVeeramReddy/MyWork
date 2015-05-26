package test.StringConcat;

public class TestStringConcatenation {
	private static final int LOOP_COUNTER = 10000;

	public static void main(String[] args) {
		long currentTimeMillis;
		long currentTimeMillis2;
		currentTimeMillis = System.currentTimeMillis();
		String str = "";
		for (int i = 0; i < LOOP_COUNTER; i++) {
			str = str + i + ",";
		}
//		System.out.println(str);
		currentTimeMillis2 = System.currentTimeMillis();
		System.out.println("Time for printing String outside "+(currentTimeMillis2-currentTimeMillis));
		
		currentTimeMillis = System.currentTimeMillis();
		String str2 = "";
		for (int i = 0; i < LOOP_COUNTER; i++) {
			StringBuilder innerBuilder = new StringBuilder();
			innerBuilder.append(i);
			innerBuilder.append(",");
			str2 = str2 + innerBuilder.toString();
		}
//		System.out.println(str2);
		currentTimeMillis2 = System.currentTimeMillis();
		System.out.println("Time for printing String outside and effecient builder inside "+(currentTimeMillis2-currentTimeMillis));
		
		currentTimeMillis = System.currentTimeMillis();
		String str3 = "";
		for (int i = 0; i < LOOP_COUNTER; i++) {
			StringBuilder innerBuilder = new StringBuilder();
			innerBuilder.append(i+",");
			str3 = str3 + innerBuilder.toString();
		}
//		System.out.println(str3);
		currentTimeMillis2 = System.currentTimeMillis();
		System.out.println("Time for printing String outside and ineffecient builder inside "+(currentTimeMillis2-currentTimeMillis));
		
		currentTimeMillis = System.currentTimeMillis();
		StringBuilder outerBuilder = new StringBuilder();
		for (int i = 0; i < LOOP_COUNTER; i++) {
			outerBuilder.append(i);
			outerBuilder.append(",");
		}
		String outerBuilderString = outerBuilder.toString();
		currentTimeMillis2 = System.currentTimeMillis();
		System.out.println("Time for printing String with only one outer efficient builder "+(currentTimeMillis2-currentTimeMillis));
		
		currentTimeMillis = System.currentTimeMillis();
		StringBuilder outerBuilder2 = new StringBuilder();
		for (int i = 0; i < LOOP_COUNTER; i++) {
			outerBuilder.append(i+",");
		}
		String outerBuilderString2 = outerBuilder2.toString();
		currentTimeMillis2 = System.currentTimeMillis();
		System.out.println("Time for printing String with one outer inefficient builder "+(currentTimeMillis2-currentTimeMillis));
		
		currentTimeMillis = System.currentTimeMillis();
		StringBuilder outerBuilder3 = new StringBuilder();
		String str4 = null;
		for (int i = 0; i < LOOP_COUNTER; i++) {
			str4 = i+",";
			outerBuilder.append(str4);
		}
		String outerBuilderString3 = outerBuilder3.toString();
		currentTimeMillis2 = System.currentTimeMillis();
		System.out.println("Time for printing String with one outer builder and inner string "+(currentTimeMillis2-currentTimeMillis));
	
	}
}
