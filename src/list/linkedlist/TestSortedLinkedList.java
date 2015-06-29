package list.linkedlist;

public class TestSortedLinkedList {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Integer[] data = new Integer[] {4,1,3};
		SortedSingleLinkedList<Integer> list = new SortedSingleLinkedList<Integer>(data);
		System.out.println(list);
	}

}
