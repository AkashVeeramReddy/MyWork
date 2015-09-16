package pattern;

public class TestStringMatcherFa {
	public static void main(String[] args) {
		StringMatcherFa fa = new StringMatcherFa("aab");
		fa.printFA();
		fa.printAllOccurences("aabaabaab");
		System.out.println();
	}
}
