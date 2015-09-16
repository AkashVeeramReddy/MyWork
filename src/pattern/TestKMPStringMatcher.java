package pattern;

public class TestKMPStringMatcher {
	public static void main(String[] args) {
		KMPStringMatcher fa = new KMPStringMatcher("aabaac");
		System.out.println(fa);
		//fa.
		//fa.printAllOccurences("aabaabaab");
		fa.printAllOccurences("aabaabaac");
		//System.out.println(fa);
	}
}
