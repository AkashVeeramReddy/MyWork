package sorting;


public class CheckSortableArrayList {
	public static void main(String[] args) {
		SortableArrayList<Integer> myLinkedList = new SortableArrayList<Integer>();
		myLinkedList.add(90);
		myLinkedList.add(40);
		myLinkedList.add(20);
		myLinkedList.add(10);
		myLinkedList.mergeSort();
		System.out.println();
	}
}
