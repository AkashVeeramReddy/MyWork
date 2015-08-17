package tree.segmenttree;

public class TestRangeMinimum {
	public static void main(String[] args) {
		Integer[] array = new Integer[]{1, 3, 2, 7, 9, 11};
		RangeMinimum minimum = new RangeMinimum(array);
		System.out.println(minimum.findMinimum(3, 5));
		
	}
}
