package pattern;

public class TestRabinKarpStringMatcher {
	public static void main(String[] args) {
		RabinKarpStringMatcher matcher = new RabinKarpStringMatcher("abab");
		matcher.printAllOccurences("abababab");
	}
}
