package tree.segmenttree;

public class TestSumInRange {
	public static void main(String[] args) {
		Integer array[] = new Integer[] {1,3,5,7,9,11};
		SumInRange sumInRange = new SumInRange(array);
		System.out.println(sumInRange);
		System.out.println("0, 5 "+sumInRange.findSum(0, 5));
		System.out.println("1, 4 "+sumInRange.findSum(1, 4));
		System.out.println("2, 4 "+sumInRange.findSum(2, 4));
		System.out.println("1, 3 "+sumInRange.findSum(1, 3));
	}
}
