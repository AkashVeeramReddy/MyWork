package tree.segmenttree;

public class TestRangeNoOfMaximum {
	public static void main(String[] args) {
		Integer[] array = new Integer[]{3, 1, 3, 2, 1};
		RangeNoOfMaximum maximum = new RangeNoOfMaximum(array);
		System.out.println("0 to 2 - "+maximum.findNoOfMaximum(0, 2));
		System.out.println("1 to 4 - "+maximum.findNoOfMaximum(1, 4));
		System.out.println("0 to 4 - "+maximum.findNoOfMaximum(0, 4));
	}
}
