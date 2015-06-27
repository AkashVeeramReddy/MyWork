package list.linkedlist;

public class TestSortedLinkedList {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SortedSingleLinkedList<Integer> list = new SortedSingleLinkedList<Integer>();
		list.add(1);
		list.add(8);
		list.add(2);
		list.add(4);
		list.add(4);
		list.add(4);
		list.add(8);
		
		SortedSingleLinkedList<Integer> list2 = new SortedSingleLinkedList<Integer>();
		list2.add(1);
		list2.add(8);
		list2.add(3);
		list2.add(4);
		list2.add(6);
		list2.add(4);
		list2.add(5);
		
		SortedSingleLinkedList<Integer> interSection = list.getInterSection(list2);
		System.out.println(interSection);
	}

}
