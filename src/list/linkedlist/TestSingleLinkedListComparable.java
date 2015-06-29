package list.linkedlist;

public class TestSingleLinkedListComparable {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SingleLinkedListComparable<Integer> list = new SingleLinkedListComparable<Integer>();
		
		
		//12->15->10->11->5->6->2->3
		
		list.add(12);
		list.add(15);
		list.add(10);
		list.add(11);
		
		list.add(5);
		list.add(6);
		list.add(2);
		list.add(3);
		
		list.deleteNodesWithGreaterValueOnRight();
		
		System.out.println(list);
	}

}
